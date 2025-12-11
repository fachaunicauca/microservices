package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTestConfigJpaRepository extends JpaRepository<StudentTestConfigTable, Long> {
    StudentTestConfigTable findByStudentEmailAndTest_TestId(String studentEmail, int testTestId);
}
