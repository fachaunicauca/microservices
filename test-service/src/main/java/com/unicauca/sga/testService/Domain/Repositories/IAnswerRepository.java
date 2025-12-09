package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Model.Answer;

import java.util.List;

public interface IAnswerRepository {
    Answer getAnswerById(long id);
    void save(Answer answer);
    void delete(Answer answer);
    void deleteById(long id);
    List<Answer> getAllAnswersByQuestion(Long question_id);
    boolean isPresent(long id);
}
