package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.Test;

import java.time.LocalDate;
import java.util.List;

public interface ITestRepository {
    List<Test> getAllTests();
    List<Test> getTeacherTests(String teacherEmail);
    Test getTestById(int id);
    Test save(Test test);
    void deleteById(int id);
    boolean isPresent(int id);
}
