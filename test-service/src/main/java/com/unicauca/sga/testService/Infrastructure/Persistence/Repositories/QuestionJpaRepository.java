package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {

    @Query("select q.questionId from QuestionEntity q where q.test.testId = :testId")
    List<Long> findIdsByTestId(@Param("testId") int testId);

    Page<QuestionEntity> findByTest_TestId(int testTestId, Pageable pageable);

    long countByTest_TestId(int testTestId);
}
