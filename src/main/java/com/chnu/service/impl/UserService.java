package com.chnu.service.impl;

import com.chnu.dto.UserDTO;
import com.chnu.model.Role;
import com.chnu.model.User;
import com.chnu.model.VerificationToken;
import com.chnu.repository.IRoleRepository;
import com.chnu.repository.IUserRepository;
import com.chnu.repository.IVerificationTokenRepository;
import com.chnu.repository.impl.RoleRepository;
import com.chnu.repository.impl.VerificationTokenRepository;
import com.chnu.service.IUserService;
import com.chnu.util.PropertiesUtil;
import com.chnu.wrapper.UserLoginWrapper;
import com.chnu.wrapper.UserRegistrationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static com.chnu.util.PropertiesUtil.getProperty;

@Service
@Transactional
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;
    private final IVerificationTokenRepository verificationTokenRepository;
    private final IRoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;
    private final LetterService letterService;

    private static final Integer maxLoginAttempts;
    static {
        String s = getProperty(PropertiesUtil.SYSTEM_PROPERTIES, "login.max.attempts");
        if(s != null) {
            maxLoginAttempts = Integer.decode(s);
        } else {
            maxLoginAttempts = 0;
        }
    }

    @Autowired
    public UserService(IUserRepository userRepository, AuthenticationManager authenticationManager,
                       BCryptPasswordEncoder passwordEncoder, VerificationTokenRepository verificationTokenRepository,
                       RoleRepository roleRepository, LetterService letterService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepository = verificationTokenRepository;
        this.roleRepository = roleRepository;
        this.letterService = letterService;
    }

    @Override
    public UserDTO register(UserRegistrationWrapper wrapper) {
        wrapper.setPassword(passwordEncoder.encode(wrapper.getPassword()));

        User user = new User()
                .setEmail(wrapper.getEmail())
                .setPassword(wrapper.getPassword())
                .setEnabled(false);

        Role role = roleRepository.findByRole(wrapper.getRole())
                .orElse(roleRepository.findByRole("USER").orElse(null));
        user.setRole(role);

        user = userRepository.save(user);

        VerificationToken token = new VerificationToken()
                .setToken(UUID.randomUUID().toString())
                .setUser(user);
        token.calculateExpiryDate();
        verificationTokenRepository.save(token);

        String url = "http://localhost:8080/user/register/confirm/" + token.getToken();
        letterService.sendEmailVerificationLetter(user.getEmail(), url);

        return UserDTO.fromUser(user);
    }

    @Override
    public boolean confirmRegistration(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token).orElse(null);
        if(verificationToken != null) {
            boolean confirmed = false;

            if(verificationToken.getExpirationDate().compareTo(new Date()) >= 0) {
                User user = verificationToken.getUser();
                user.setEnabled(true);
                userRepository.update(user);
                confirmed = true;
            }
            verificationTokenRepository.delete(verificationToken);

            return confirmed;
        }
        return false;
    }

    @Override
    public boolean checkEmailAvailable(String email) {
        return !userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserDTO login(UserLoginWrapper wrapper) {
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(wrapper.getEmail(), wrapper.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            User user = (User)authentication.getPrincipal();
            if(user.getLock() != null) {
                user.setLock(null);
                userRepository.update(user);
            }

            return UserDTO.fromUser(user).setCorrectCredentials(true);
        } catch (BadCredentialsException | InternalAuthenticationServiceException ex) {
            User user = userRepository.findByEmail(wrapper.getEmail()).orElse(null);

            if(user != null) {
                int failCount = user.getFailAttempts() != null ? user.getFailAttempts() : 0;
                int maxAttempts = maxLoginAttempts != null ? maxLoginAttempts : 3;
                failCount++;

                if(failCount < maxAttempts) {
                    user.setFailAttempts(failCount);
                } else {
                    user.setFailAttempts(0);
                    user.setLock(new Date());
                }
                userRepository.update(user);

                return new UserDTO()
                        .setEmail(wrapper.getEmail())
                        .setCorrectCredentials(false)
                        .setLocked(!user.isAccountNonLocked());
            }
            return new UserDTO()
                    .setEmail(wrapper.getEmail())
                    .setCorrectCredentials(false);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        if(id != null) {
            return userRepository.findById(id);
        }
        return Optional.empty();
    }
}
