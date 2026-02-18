package com.unicauca.sga.testService.Domain.Services;

import com.unicauca.sga.testService.Domain.Models.TestResults.StudentTestResult;

import java.util.List;

public interface ICourseService{
    boolean courseExistsById(int id);
    boolean isStudentInCourse(String studentEmail, int courseId);
    Iterable<StudentTestResult> getCourseStudents(int courseId, int page, int size);
    List<String> getCourseStudentsEmails(int courseId);
}
