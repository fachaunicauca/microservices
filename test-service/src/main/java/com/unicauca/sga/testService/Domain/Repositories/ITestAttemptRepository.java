package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.TestAttempt;

import java.util.List;

public interface ITestAttemptRepository {
    List<TestAttempt> getAllStudentTestAttempts(String studentEmail, int testId);
    void save(TestAttempt testAttempt);
    void deleteById(long id);
}
