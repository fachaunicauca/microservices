package com.unicauca.sga.testService.Domain.Repositories;


import com.unicauca.sga.testService.Domain.Models.TestGuide;

import java.util.List;
import java.util.Optional;

public interface ITestGuidesRepository {
    Optional<TestGuide> getTestGuide(String id);
    Iterable<TestGuide> getAllTestsGuidesFiltered(String filterKey, String filterValue, int page, int size);
    Iterable<TestGuide> getTeacherTestsGuidesFiltered(String filterKey, String filterValue, String teacherEmail, int page, int size);
    TestGuide save(TestGuide testGuide);
    void deleteById(String id);
    boolean isPresent(String id);
}
