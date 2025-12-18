package com.unicauca.sga.testService.Domain.Repositories;


import com.unicauca.sga.testService.Domain.Models.Question;

import java.util.List;

public interface IQuestionRepository {
    void save(Question question);
    void deleteById(long id);
    boolean isPresent(long id);
    List<Question> getRandomAndLimitedTestQuestions(int testId, int numberOfQuestions);
    List<Question> getTestQuestions(int id);
    long getTestTotalQuestions(int testId);
}
