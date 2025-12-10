package com.unicauca.sga.testService.Domain.Repositories;


import com.unicauca.sga.testService.Domain.Models.Question;

import java.util.List;

public interface IQuestionRepository {
    void save(Question question);
    void deleteById(long id);
    List<Question> getRandomQuestionsBySubject(String subject_name, int n);
    boolean isPresent(long id);
    List<Question> getTestQuestions(int testId);
}
