package com.unicauca.sga.testService.Domain.Ports.Services;

import com.unicauca.sga.testService.Domain.Model.Subject;

import java.util.List;

public interface ISubjectService {
    List<Subject> getAllSubjects();
    Subject getSubjectById(String id);
    void saveSubject(Subject subject);
    void deleteSubject(Subject subject);
    void deleteSubjectById(String id);
    void updateSubject(Subject subject);
    boolean isPresent(String id);
}
