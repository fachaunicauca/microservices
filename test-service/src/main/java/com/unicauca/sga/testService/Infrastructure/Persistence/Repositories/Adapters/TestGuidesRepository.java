package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.TestGuide;
import com.unicauca.sga.testService.Domain.Repositories.ITestGuidesRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.TestGuideMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Specifications.TestGuideSpecifications;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.TestGuidesJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestGuideEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public Page<TestGuide> getAllTestsGuidesFiltered(String filterKey,
                                                     String filterValue,
                                                     int page, int size) {
        Specification<TestGuideEntity> spec = Specification
                .where(TestGuideSpecifications.withFilter(filterKey, filterValue));

        return testGuidesJpaRepository.findAll(spec, PageRequest.of(page,size)).map(testGuideMapper::toModel);
    }

    @Override
    public Page<TestGuide> getTeacherTestsGuidesFiltered(String filterKey,
                                                         String filterValue,
                                                         String teacherEmail,
                                                         int page, int size) {
        Specification<TestGuideEntity> spec = Specification
                .where(TestGuideSpecifications.withFilter(filterKey, filterValue))
                .and(TestGuideSpecifications.byTeacher(teacherEmail));

        return testGuidesJpaRepository.findAll(spec, PageRequest.of(page,size)).map(testGuideMapper::toModel);
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
