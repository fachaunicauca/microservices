package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface StudentResponseJpaRepository extends JpaRepository<StudentResponseEntity,Long> {
    List<StudentResponseEntity> findByQuestionId(long questionId);

    List<StudentResponseEntity> findByTestAttempt_TestAttemptId(long testAttemptTestAttemptId);
}
