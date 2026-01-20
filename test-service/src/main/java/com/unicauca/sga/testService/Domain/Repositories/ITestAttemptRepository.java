package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.TestAttempt;

import java.util.Optional;

public interface ITestAttemptRepository {
    Optional<TestAttempt> getTestAttemptById(long id);
    void save(TestAttempt testAttempt);
    void deleteById(long id);
}
