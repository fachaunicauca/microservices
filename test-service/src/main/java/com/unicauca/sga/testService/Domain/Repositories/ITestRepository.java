package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Model.Test;
import java.time.LocalDate;
import java.util.List;

public interface ITestRepository {
    List<Test> getAllTests();
    Test getTestById(long id);
    Test save(Test test);
    void delete(Test test);
    void deleteById(long id);
    boolean isPresent(long id);
    List<Test> getTestBySemesterAndStudentCode(LocalDate startDate, LocalDate endDate, Long studentId);
}
