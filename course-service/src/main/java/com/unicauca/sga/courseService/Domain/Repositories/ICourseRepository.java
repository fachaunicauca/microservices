package com.unicauca.sga.courseService.Domain.Repositories;

import com.unicauca.sga.courseService.Domain.Models.Course;
import com.unicauca.sga.courseService.Domain.Models.Student;

import java.util.Optional;

public interface ICourseRepository {
    Optional<Course> getCourseById(int id);
    Iterable<Course> getTeacherCoursesPaged(String teacherEmail, int page, int size);
    Iterable<Course> getAllCoursesPaged(int page, int size);
    Course saveCourse(Course course);
    void deleteById(int id);
    boolean isPresent(int id);
}
