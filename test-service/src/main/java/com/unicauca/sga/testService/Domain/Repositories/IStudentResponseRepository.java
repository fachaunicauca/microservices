package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;

import java.util.List;

public interface IStudentResponseRepository {
    void save(StudentResponse studentResponse);
    boolean isPresent(long id);
    List<StudentResponse> getAllQuestionResponses(long questionId);
    List<StudentResponse> getAllTestAttemptResponses(long testAttempt);
}
