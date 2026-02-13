package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;

import java.util.Optional;

public interface IStudentTestConfigRepository {
    void save(StudentTestConfig studentTestConfig);
    Optional<StudentTestConfig> getStudentTestConfig(String studentEmail, int testId);

    Iterable<StudentTestConfig> getConfigsWithPendingAttemptRequest(int testId, int page, int size);
}
