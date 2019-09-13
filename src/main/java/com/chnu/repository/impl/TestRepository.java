package com.chnu.repository.impl;

import com.chnu.model.TestModel;
import com.chnu.repository.ITestRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository extends AbstractRepository<TestModel, Long> implements ITestRepository {
}
