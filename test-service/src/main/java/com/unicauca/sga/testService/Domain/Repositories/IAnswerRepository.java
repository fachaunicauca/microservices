package com.unicauca.sga.testService.Domain.Repositories;



import com.unicauca.sga.testService.Domain.Models.Answer;

import java.util.List;

public interface IAnswerRepository {
    void save(Answer answer);
    void deleteById(long id);
    List<Answer> getQuestionAnswers(long questionId);
    boolean isPresent(long id);
}
