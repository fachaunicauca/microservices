package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Models.StudentTestResult;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IStudentTestConfigRepository {
    void save(StudentTestConfig studentTestConfig);
    Optional<StudentTestConfig> getStudentTestConfig(String studentEmail, int testId);
    Iterable<StudentTestConfig> getConfigsWithPendingAttemptRequest(int testId, int page, int size);
    List<StudentTestConfig> getAllByTestIdAndStudentEmailIn(int testId, Collection<String> emails);
    Iterable<StudentTestResult> getAllResultsByTestId(int testId, int page, int size);
}
