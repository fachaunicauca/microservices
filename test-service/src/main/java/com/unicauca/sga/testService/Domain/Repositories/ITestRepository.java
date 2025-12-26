package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ITestRepository {
    Page<Test> getAllTests(Pageable pageable);
    Page<Test> getTeacherTests(String teacherEmail, Pageable pageable);
    Test getTestById(int id);
    Test save(Test test);
    void deleteById(int id);
    boolean isPresent(int id);
}
