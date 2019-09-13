package com.chnu.controller;

import com.chnu.model.TestModel;
import com.chnu.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class TestController {

    private final ITestService testService;

    @Autowired
    public TestController(ITestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "ping")
    public String ping() {
        return testService.ping();
    }

    @PostMapping(value = "addTestModel")
    public TestModel addTestModel() {
        return testService.addTestModel();
    }
}
