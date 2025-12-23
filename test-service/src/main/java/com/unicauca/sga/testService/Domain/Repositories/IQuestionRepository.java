package com.unicauca.sga.testService.Domain.Repositories;


import com.unicauca.sga.testService.Domain.Models.Question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IQuestionRepository {
    void save(Question question);
    void deleteById(long id);
    boolean isPresent(long id);
    List<Question> getRandomAndLimitedTestQuestions(int testId, int numberOfQuestions);
    Page<Question> getTestQuestionsPaged(int id, Pageable pageable);
    long getTestTotalQuestions(int testId);
}
