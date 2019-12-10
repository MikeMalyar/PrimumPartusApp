package com.chnu.controller;

import com.chnu.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

    private final UserService userService;

    @Autowired
    public ViewController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String indexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "security/registration";
    }

    @GetMapping("/user/register/confirm/{token}")
    public String confirm(@PathVariable(name = "token") String token) {
        if(userService.confirmRegistration(token)) {
            return "security/login";
        } else {
            return "security/verificationError";
        }
    }

}
