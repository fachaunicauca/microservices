package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionEntity;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionJpaRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findByTest_TestId(int testTestId, Limit limit);
    Page<QuestionEntity> findByTest_TestId(int testTestId, Pageable pageable);

    long countByTest_TestId(int testTestId);
}
