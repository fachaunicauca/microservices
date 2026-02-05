package com.unicauca.sga.courseService.Domain.Repositories;

import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Domain.Models.StudentEnrollment;

import java.util.Optional;

public interface IStudentEnrollmentRepository {
    Iterable<Student> getCourseStudents(int courseId, int page, int size);
    Optional<StudentEnrollment> getStudentEnrollment(long studentId, int courseId);
    StudentEnrollment saveStudentEnrollment(StudentEnrollment studentEnrollment);
    boolean isStudentInCourse(long studentId, int courseId);
    void deleteById(long id);
}
