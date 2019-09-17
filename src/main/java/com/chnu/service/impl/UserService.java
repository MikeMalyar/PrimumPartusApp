package com.chnu.service.impl;

import com.chnu.model.Role;
import com.chnu.model.User;
import com.chnu.model.UserPrincipal;
import com.chnu.repository.IUserRepository;
import com.chnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
import java.util.Collections;

@Service
@Transactional
public class UserService implements IUserService, UserDetailsService {

    private final IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(IUserRepository userRepository, AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean checkEmailAvailable(String email) {
        return !userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);

        if(user != null) {
            return new UserPrincipal(
                    user.getEmail(),
                    user.getPassword(),
                    Collections.singletonList(user.getRole()),
                    user.getUserId());
        }
        return null;
    }

    public User login(User user) {
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
            org.springframework.security.core.userdetails.User principal =
                    (org.springframework.security.core.userdetails.User)authentication.getPrincipal();

            return new User().setEmail(principal.getUsername()).setPassword(principal.getPassword())
                    .setRole((Role) principal.getAuthorities().toArray()[0]);
        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
