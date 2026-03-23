package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestAttemptJpaRepository extends JpaRepository<TestAttemptEntity, Long> {
    List<TestAttemptEntity> findAllByStudentEmailAndTestId(String studentEmail, Integer testId);
}
