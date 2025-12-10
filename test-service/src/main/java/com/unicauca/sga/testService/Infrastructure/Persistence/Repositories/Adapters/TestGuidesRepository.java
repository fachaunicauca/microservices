package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.TestGuide;
import com.unicauca.sga.testService.Domain.Repositories.ITestGuidesRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.TestGuideMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.TestGuidesJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TestGuidesRepository implements ITestGuidesRepository {

    private final TestGuidesJpaRepository testGuidesJpaRepository;
    private final TestGuideMapper testGuideMapper;

    public TestGuidesRepository(TestGuidesJpaRepository testGuidesJpaRepository,
                                TestGuideMapper testGuideMapper) {
        this.testGuidesJpaRepository = testGuidesJpaRepository;
        this.testGuideMapper = testGuideMapper;
    }

    @Override
    public List<TestGuide> getAllTestsGuides() {
        return testGuidesJpaRepository.findAll().stream().map(testGuideMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<TestGuide> getTeacherTestsGuides(String teacherEmail) {
        return testGuidesJpaRepository.findByTeacherEmail(teacherEmail);
    }

    @Override
    public TestGuide save(TestGuide testGuide) {
        return testGuideMapper.toModel(testGuidesJpaRepository.save(testGuideMapper.toInfra(testGuide)));
    }

    @Override
    public void deleteById(String id) {
        testGuidesJpaRepository.deleteById(id);
    }

    @Override
    public boolean isPresent(String id) {
        return testGuidesJpaRepository.findById(id).isPresent();
    }
}
