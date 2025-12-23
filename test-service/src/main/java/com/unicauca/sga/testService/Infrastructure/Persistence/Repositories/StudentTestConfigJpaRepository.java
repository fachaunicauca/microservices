package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentTestConfigJpaRepository extends JpaRepository<StudentTestConfigEntity, Long> {
    StudentTestConfigEntity findByStudentEmailAndTest_TestId(String studentEmail, int testTestId);
}
