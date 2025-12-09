package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.Test;

import java.time.LocalDate;
import java.util.List;

public interface ITestRepository {
    List<Test> getAllTests();
    Test getTestById(long id);
    Test save(Test test);
    void deleteById(long id);
    boolean isPresent(long id);
}
