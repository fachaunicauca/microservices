package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionJpaRepository extends JpaRepository<QuestionTable, Long> {
    @Query("SELECT q FROM QuestionTable q JOIN FETCH q.questionTopic WHERE q.subject.subject_name = :subject_name ORDER BY RAND()")
    List<QuestionTable> findRandomBySubject(@Param("subject_name") String subject_name, Pageable pageable);

}
