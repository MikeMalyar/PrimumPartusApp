package com.chnu.service.impl;

import com.chnu.model.TestModel;
import com.chnu.repository.ITestRepository;
import com.chnu.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TestService implements ITestService {

    private final ITestRepository testRepository;

    @Autowired
    public TestService(ITestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public String ping() {
        return "pong";
    }

    @Override
    public TestModel addTestModel() {
        TestModel model = new TestModel();
        model.setTest("Hello!");

        return testRepository.save(model);
    }
}
