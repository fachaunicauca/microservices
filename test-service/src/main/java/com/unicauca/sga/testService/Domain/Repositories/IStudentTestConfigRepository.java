package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IStudentTestConfigRepository {
    void save(StudentTestConfig studentTestConfig);
    Optional<StudentTestConfig> getStudentTestConfig(String studentEmail, int testId);

    Page<StudentTestConfig> getConfigsWithPendingAttemptRequest(int testId, Pageable pageable);
    Page<StudentTestConfig> getConfigsWithoutAttemptRequest(int testId, Pageable pageable);
}
