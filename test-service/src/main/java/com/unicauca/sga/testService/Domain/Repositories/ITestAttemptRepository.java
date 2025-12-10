package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.TestAttempt;

public interface ITestAttemptRepository {
    TestAttempt getTestAttemptById(long id);
    void save(TestAttempt testAttempt);
    void deleteById(long id);
    boolean isPresent(long id);
}
