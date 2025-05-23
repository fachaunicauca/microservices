package com.unicauca.sga.testService.Aplication.Services;

import com.unicauca.sga.testService.Domain.Model.TestGuide;
import com.unicauca.sga.testService.Domain.Ports.Repositories.ITestGuidesRepository;
import com.unicauca.sga.testService.Domain.Ports.Services.ITestGuidesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestGuidesService implements ITestGuidesService {

    private final ITestGuidesRepository testGuidesRepository;

    public TestGuidesService(ITestGuidesRepository testGuidesRepository) {this.testGuidesRepository = testGuidesRepository;}

    @Override
    public List<TestGuide> getAllTestsGuides() {
        return testGuidesRepository.findAll();
    }

    @Override
    public TestGuide getTestGuideById(String id) {
        return testGuidesRepository.findById(id);
    }

    @Override
    public TestGuide saveTestGuide(TestGuide test) {
        return testGuidesRepository.save(test);
    }

    @Override
    public void deleteTestGuide(TestGuide test) {
        testGuidesRepository.delete(test);
    }

    @Override
    public void deleteTestGuideById(String id) {
        testGuidesRepository.deleteById(id);
    }

    @Override
    public TestGuide updateTestGuide(TestGuide test) {
        return testGuidesRepository.save(test);
    }

    @Override
    public boolean isPresent(String id) {
        return testGuidesRepository.isPresent(id);
    }
}
