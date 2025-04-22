package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerJpaRepository extends JpaRepository<AnswerTable, Long> {
    @Query("SELECT a FROM AnswerTable a WHERE a.question.question_id = :question_id")
    List<AnswerTable> findByQuestionId(Long question_id);
    @Query("SELECT a.answer_isCorrect FROM AnswerTable a WHERE a.answer_id = :answer_id")
    boolean isCorrect(@Param("answer_id") long answer_id);
}
