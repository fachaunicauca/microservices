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

    @Override
    public TestAttempt getTestAttemptById(long id) {
        return testAttemptMapper.toModel(testAttemptJpaRepository.findById(id).get());
    }

    @Override
    public void save(TestAttempt testAttempt) {
        testAttemptJpaRepository.save(testAttemptMapper.toInfra(testAttempt));
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
