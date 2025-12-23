package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import com.unicauca.sga.testService.Domain.Repositories.ITestAttemptRepository;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.TestAttemptMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.TestAttemptJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestAttemptEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TestAttemptRepository implements ITestAttemptRepository {

    private final TestAttemptJpaRepository testAttemptJpaRepository;
    private final TestAttemptMapper testAttemptMapper;

    private TestAttempt toModel(TestAttemptEntity testAttempt) {
        if (testAttempt == null) return null;
        return testAttemptMapper.toModel(testAttempt, new CycleAvoidingMappingContext());
    }

    private TestAttemptEntity toInfra(TestAttempt testAttempt) {
        if (testAttempt == null) return null;
        return testAttemptMapper.toInfra(testAttempt, new CycleAvoidingMappingContext());
    }

    @Override
    public TestAttempt getTestAttemptById(long id) {
        return toModel(testAttemptJpaRepository.findById(id).get());
    }

    @Override
    public void save(TestAttempt testAttempt) {
        testAttemptJpaRepository.save(toInfra(testAttempt));
    }

    @Override
    public void deleteById(long id) {
        testAttemptJpaRepository.deleteById(id);
    }

    @Override
    public boolean isPresent(long id) {
        return testAttemptJpaRepository.existsById(id);
    }
}
