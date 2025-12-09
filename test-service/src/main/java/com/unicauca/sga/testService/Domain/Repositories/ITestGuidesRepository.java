package com.unicauca.sga.testService.Domain.Repositories;


import com.unicauca.sga.testService.Domain.Models.TestGuide;

import java.util.List;

public interface ITestGuidesRepository {
    List<TestGuide> getAllTestsGuides();
    List<TestGuide> getTeacherTestsGuides(String teacherEmail);
    TestGuide save(TestGuide testGuide);
    void deleteById(String id);
    boolean isPresent(String id);
}
