package com.chnu.controller.base;

import com.chnu.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public abstract class BaseController {

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }

    protected void validate(Object object) {
        Validator.validate(object);
    }

}
