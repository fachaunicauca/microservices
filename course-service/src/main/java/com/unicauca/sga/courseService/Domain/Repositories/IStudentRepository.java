package com.unicauca.sga.courseService.Domain.Repositories;

import com.unicauca.sga.courseService.Domain.Models.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentRepository {
    Optional<Student> getStudentByEmail(String email);
    Iterable<Student> getAllStudentsPaged(int page, int size);
    List<Student> getAllStudentsByEmails(List<String> emails);
    Student saveStudent(Student student);
    void deleteById(long studentId);
    boolean isPresent(long studentId);
}
