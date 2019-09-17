package com.chnu.controller;

import com.chnu.model.User;
import com.chnu.rest.GenericResponse;
import com.chnu.service.IUserService;
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
    public GenericResponse<User> register(@RequestBody User user) {
        return GenericResponse.of(userService.register(user));
    }

    @PostMapping("/login")
    public GenericResponse<User> login(@RequestBody User user) {
        return GenericResponse.of(userService.login(user));
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
}
