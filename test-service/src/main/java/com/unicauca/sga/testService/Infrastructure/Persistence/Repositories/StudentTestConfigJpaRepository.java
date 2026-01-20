package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentTestConfigJpaRepository extends JpaRepository<StudentTestConfigEntity, Long> {
    Optional<StudentTestConfigEntity> findByStudentEmailAndTest_TestId(String studentEmail, int testTestId);

    boolean existsByStudentEmailAndTest_TestId(String studentEmail, Integer testTestId);
}
