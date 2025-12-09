package com.unicauca.sga.testService.Domain.Repositories;


import com.unicauca.sga.testService.Domain.Models.Question;

import java.util.List;

public interface IQuestionRepository {
    Question getQuestionById(long id);
    void save(Question question);
    void deleteById(long id);
    List<Question> getRandomQuestionsBySubject(String subject_name, int n);
    boolean isPresent(long id);
}
