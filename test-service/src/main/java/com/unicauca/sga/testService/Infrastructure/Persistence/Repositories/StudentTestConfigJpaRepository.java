package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentTestConfigJpaRepository extends JpaRepository<StudentTestConfigEntity, Long> {
    Optional<StudentTestConfigEntity> findByStudentEmailAndTest_TestId(String studentEmail, int testTestId);

    boolean existsByStudentEmailAndTest_TestId(String studentEmail, Integer testTestId);

    Page<StudentTestConfigEntity> findByAttemptRequestStatusAndTest_TestId(String attemptRequestStatus, Integer testTestId, Pageable pageable);

    Page<StudentTestConfigEntity> findByAttemptRequestStatusNotAndTest_TestId(String attemptRequestStatus, Integer testTestId, Pageable pageable);
}
