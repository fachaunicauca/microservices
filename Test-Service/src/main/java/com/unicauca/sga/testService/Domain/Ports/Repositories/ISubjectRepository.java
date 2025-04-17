package com.unicauca.sga.testService.Domain.Ports.Repositories;

import com.unicauca.sga.testService.Domain.Model.Subject;

import java.util.List;

public interface ISubjectRepository {
    List<Subject> findAll();
    Subject findById(String id);
    void save(Subject subject);
    void delete(Subject subject);
    void deleteById(String id);
    boolean isPresent(String id);
}
