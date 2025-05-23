package com.unicauca.sga.testService.Domain.Ports.Repositories;

import com.unicauca.sga.testService.Domain.Model.TestGuide;

import java.util.List;

public interface ITestGuidesRepository {
    List<TestGuide> findAll();
    TestGuide findById(String id);
    TestGuide save(TestGuide testGuide);
    void delete(TestGuide testGuide);
    void deleteById(String id);
    boolean isPresent(String id);
}
