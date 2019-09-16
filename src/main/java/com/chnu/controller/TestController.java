package com.chnu.controller;

import com.chnu.model.Role;
import com.chnu.model.User;
import com.chnu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test/")
public class TestController {

    private final IUserService userService;

    @Autowired
    public TestController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "ping")
    public String ping() {
        return "pong";
    }

    @PostMapping(value = "createUser")
    public ResponseEntity<User> testCreateUser() {
        User user = new User()
                .setEmail("john.doe@gmail.com")
                .setPassword("****")
                .setEnabled(false)
                .setRole(new Role().setRole("ADMIN"));

        return ResponseEntity.ok().body(userService.register(user));
    }

    @PostMapping(value = "addUser")
    public ResponseEntity<User> testAddUser(@RequestBody User user) {
        return ResponseEntity.ok().body(userService.register(user));
    }
}
