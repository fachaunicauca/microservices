package com.unicauca.sga.testService.Domain.Services;

import com.unicauca.sga.testService.Domain.Models.StudentTestResult;

import java.util.Collection;
import java.util.List;

public interface IStudentService {
    List<StudentTestResult> getStudentsByEmails(Collection<String> emails);
}
