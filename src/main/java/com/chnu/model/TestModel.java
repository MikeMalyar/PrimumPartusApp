package com.chnu.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test_model")
public class TestModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_model_id")
    private Long testModelId;

    @Column(name = "test", nullable = true, length = 30)
    private String test;

    public Long getTestModelId() {
        return testModelId;
    }

    public TestModel setTestModelId(Long testModelId) {
        this.testModelId = testModelId;
        return this;
    }

    public String getTest() {
        return test;
    }

    public TestModel setTest(String test) {
        this.test = test;
        return this;
    }
}
