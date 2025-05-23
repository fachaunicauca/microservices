package com.unicauca.sga.testService.Domain.Ports.Services;

import com.unicauca.sga.testService.Domain.Model.TestGuide;

import java.util.List;

public interface ITestGuidesService {
    List<TestGuide> getAllTestsGuides();
    TestGuide getTestGuideById(String id);
    TestGuide saveTestGuide(TestGuide test);
    void deleteTestGuide(TestGuide test);
    void deleteTestGuideById(String id);
    TestGuide updateTestGuide(TestGuide test);
    boolean isPresent(String id);
}
