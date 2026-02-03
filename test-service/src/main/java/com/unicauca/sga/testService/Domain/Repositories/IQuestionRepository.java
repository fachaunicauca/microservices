package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Models.Question.Question;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface IQuestionRepository {
    Question save(Question question);
    void deleteById(long id);
    Optional<Question> getById(long id);
    List<Question> getRandomAndLimitedTestQuestions(int testId, int numberOfQuestions);
    Iterable<Question> getTestQuestionsPaged(int id, int page, int size);
    long getTestTotalQuestions(int testId);
    List<Question> getByIds(Collection<Long> ids);
}
