package com.chnu.controller;

import com.chnu.dto.UserDTO;
import com.chnu.rest.GenericResponse;
import com.chnu.service.IUserService;
import com.chnu.wrapper.UserLoginWrapper;
import com.chnu.wrapper.UserRegistrationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final IUserService userService;

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
            return GenericResponse.withSuccessMessage("Successfully confirmed registration.");
        } else {
            return GenericResponse.error("Confirmation link has expired.");
        }
    }

    @PostMapping("/login")
    public GenericResponse<UserDTO> login(@RequestBody UserLoginWrapper wrapper) {
        UserDTO user = userService.login(wrapper);
        if(!user.getCorrectCredentials()) {
            return GenericResponse.error("Username or password is incorrect.");
        }
        return GenericResponse.of(user);
    }

    @PostMapping("/logout")
    public GenericResponse logout(SessionStatus session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.setComplete();
        return new GenericResponse().setMessage("Successfully logged out.");
    }

    @GetMapping("/checkEmailAvailable")
    public GenericResponse<Boolean> checkEmailAvailable(@RequestParam(name = "email") String email) {
        Boolean isAvailable = userService.checkEmailAvailable(email);
        return GenericResponse.of(isAvailable).setSuccess(true);
    }

    private GenericResponse<UserDTO> checkRegistration(UserRegistrationWrapper wrapper) {
        if(!userService.checkEmailAvailable(wrapper.getEmail())) {
            return GenericResponse.error("Email isn't available");
        }
        if(!wrapper.getPassword().equals(wrapper.getConfirmPassword())) {
            return GenericResponse.error("Passwords don't match.");
        }
        return null;
    }
}
