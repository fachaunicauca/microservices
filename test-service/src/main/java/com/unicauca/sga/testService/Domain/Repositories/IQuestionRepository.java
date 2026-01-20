package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IQuestionRepository {
    Question save(Question question);
    void deleteById(long id);
    Optional<Question> getById(long id);
    List<Question> getRandomAndLimitedTestQuestions(int testId, int numberOfQuestions);
    Page<Question> getTestQuestionsPaged(int id, Pageable pageable);
    long getTestTotalQuestions(int testId);
}
