package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionJpaRepository extends JpaRepository<QuestionTable, Long> {

    List<QuestionTable> findByTest_TestId(int testTestId, Limit limit);
    List<QuestionTable> findByTest_TestId(int testTestId);
}
