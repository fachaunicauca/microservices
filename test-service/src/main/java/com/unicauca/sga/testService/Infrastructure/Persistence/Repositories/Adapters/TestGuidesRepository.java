package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.TestGuide;
import com.unicauca.sga.testService.Domain.Repositories.ITestGuidesRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.TestGuideMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.TestGuidesJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TestGuidesRepository implements ITestGuidesRepository {

    private final TestGuidesJpaRepository testGuidesJpaRepository;
    private final TestGuideMapper testGuideMapper;

    @Override
    public Optional<TestGuide> getTestGuide(String id) {
        return testGuidesJpaRepository.findById(id).map(testGuideMapper::toModel);
    }

    @Override
    public List<TestGuide> getAllTestsGuides() {
        return testGuidesJpaRepository.findAll().stream().map(testGuideMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<TestGuide> getTeacherTestsGuides(String teacherEmail) {
        return testGuidesJpaRepository.findByTeacherEmail(teacherEmail).stream().map(testGuideMapper::toModel).toList();
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
