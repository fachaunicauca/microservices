package com.unicauca.sga.testService.Aplication.Services;

public interface ICourseService{
    boolean courseExistsById(int id);
    boolean isStudentInCourse(String studentEmail, int courseId);
}
