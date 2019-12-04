package com.chnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ViewController {

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

}
