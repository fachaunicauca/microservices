package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.Test;

import java.util.Optional;

public interface ITestRepository {
    Iterable<Test> getAllTestsFiltered(String filterKey, String filterValue, int page, int size);
    Iterable<Test> getTeacherTestsFiltered(String filterKey, String filterValue, String teacherEmail, int page, int size);
    Iterable<Test> getAllActiveTestsFiltered(String filterKey, String filterValue, int page, int size);
    Optional<Test> getTestById(int id);
    Test save(Test test);
    void deleteById(int id);
    boolean isPresent(int id);
}
