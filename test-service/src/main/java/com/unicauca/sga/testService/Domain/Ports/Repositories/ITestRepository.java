package com.unicauca.sga.testService.Domain.Ports.Repositories;

import com.unicauca.sga.testService.Domain.Model.Test;

import java.util.List;

public interface ITestRepository {
    List<Test> findAll();
    Test findById(long id);
    Test save(Test test);
    void delete(Test test);
    void deleteById(long id);
    boolean isPresent(long id);
}
