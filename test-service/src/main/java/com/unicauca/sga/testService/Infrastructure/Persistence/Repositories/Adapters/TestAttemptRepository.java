package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import com.unicauca.sga.testService.Domain.Repositories.ITestAttemptRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.TestAttemptMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.TestAttemptJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestAttemptRepository implements ITestAttemptRepository {

    private final TestAttemptJpaRepository testAttemptJpaRepository;
    private final TestAttemptMapper testAttemptMapper;

    @Override
    public List<TestAttempt> getAllStudentTestAttempts(String studentEmail, int testId) {
        return testAttemptJpaRepository.findAllByStudentEmailAndTestId(studentEmail, testId).stream().map(testAttemptMapper::toModel).toList();
    }

    @Override
    public void save(TestAttempt testAttempt) {
        testAttemptJpaRepository.save(testAttemptMapper.toInfra(testAttempt));
    }

    @Override
    public void deleteById(long id) {
        testAttemptJpaRepository.deleteById(id);
    }
}
