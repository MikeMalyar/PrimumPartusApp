package com.chnu.controller;

import com.chnu.dto.UserDTO;
import com.chnu.rest.GenericResponse;
import com.chnu.service.IUserService;
import com.chnu.util.LoggerUtil;
import com.chnu.util.PropertiesUtil;
import com.chnu.wrapper.UserLoginWrapper;
import com.chnu.wrapper.UserRegistrationWrapper;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import static com.chnu.util.PropertiesUtil.getMessage;
import static com.chnu.util.PropertiesUtil.getProperty;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final IUserService userService;

    private static Logger logger = LoggerUtil.getLogger(UserController.class);

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public GenericResponse<UserDTO> register(@RequestBody UserRegistrationWrapper wrapper) {
        GenericResponse<UserDTO> response = checkRegistration(wrapper);
        if(response == null) {
            response = GenericResponse.of(userService.register(wrapper));
        }
        return response;
    }

    @PostMapping("/register/confirm")
    public GenericResponse confirm(@RequestParam(name = "token", required = true) String token) {
        if(userService.confirmRegistration(token)) {
            return GenericResponse.withSuccessMessage(getMessage("msg.registration.success"));
        } else {
            return GenericResponse.error(getMessage("msg.registration.expired"));
        }
    }

    @PostMapping("/login")
    public GenericResponse<UserDTO> login(@RequestBody UserLoginWrapper wrapper) {
        UserDTO user = userService.login(wrapper);
        if(user.getLocked()) {
            logger.warn(String.format("User account %s was blocked", wrapper.getEmail()));
            return GenericResponse.error(String.format(getMessage("msg.account.locked"),
                    getProperty(PropertiesUtil.SYSTEM_PROPERTIES, "account.lock.minutes")));
        }
        if(!user.getCorrectCredentials()) {
            logger.warn("Bad credentials entered for user " + wrapper.getEmail());
            return GenericResponse.error(getMessage("msg.bad.credentials"));
        }
        logger.info("Logged in user " + wrapper.getEmail());
        return GenericResponse.of(user);
    }

    @PostMapping("/logout")
    public GenericResponse logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return new GenericResponse().setMessage(getMessage("msg.logged.out"));
    }

    @GetMapping("/checkEmailAvailable")
    public GenericResponse<Boolean> checkEmailAvailable(@RequestParam(name = "email") String email) {
        Boolean isAvailable = userService.checkEmailAvailable(email);
        return GenericResponse.of(isAvailable).setSuccess(true);
    }

    private GenericResponse<UserDTO> checkRegistration(UserRegistrationWrapper wrapper) {
        if(!userService.checkEmailAvailable(wrapper.getEmail())) {
            return GenericResponse.error(getMessage("msg.email.unavailable"));
        }
        if(!wrapper.getPassword().equals(wrapper.getConfirmPassword())) {
            return GenericResponse.error(getMessage("msg.passwords.dont.match"));
        }
        return null;
    }
}
