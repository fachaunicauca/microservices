package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;

public interface IStudentTestConfigRepository {
    void save(StudentTestConfig studentTestConfig);
    StudentTestConfig getStudentTestConfig(String studentEmail, int testId);
}
