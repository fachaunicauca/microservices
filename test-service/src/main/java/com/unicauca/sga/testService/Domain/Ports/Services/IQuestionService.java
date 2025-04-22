package com.unicauca.sga.testService.Domain.Ports.Services;

import com.unicauca.sga.testService.Domain.Model.Question;

import java.util.List;

public interface IQuestionService {
    List<Question> getAllQuestions();
    Question getQuestionById(long id);
    void saveQuestion(Question question);
    void deleteQuestion(Question question);
    void deleteQuestionById(long id);
    void updateQuestion(Question question);
    List<Question> getRandomQuestionsBySubject(String subject_name, int n);
    boolean isPresent(long id);
}
