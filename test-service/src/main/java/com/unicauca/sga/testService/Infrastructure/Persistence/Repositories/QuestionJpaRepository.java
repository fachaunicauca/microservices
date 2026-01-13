package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionEntity;
import org.springdoc.core.converters.models.Sort;
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

    long countByTest_TestId(int testTestId);

    Page<QuestionEntity> findByTest_TestIdOrderByQuestionId(Integer testTestId, Pageable pageable);
}
