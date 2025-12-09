package com.unicauca.sga.testService.Domain.Repositories;


import com.unicauca.sga.testService.Domain.Model.Question;

import java.util.List;

public interface IQuestionRepository {
    Question getQuestionById(long id);
    void save(Question question);
    void delete(Question question);
    void deleteById(long id);
    List<Question> getRandomQuestionsBySubject(String subject_name, int n);
    boolean isPresent(long id);
}
