package com.unicauca.sga.testService.Domain.Ports.Repositories;


import com.unicauca.sga.testService.Domain.Model.Question;
import com.unicauca.sga.testService.Domain.Model.QuestionTopic;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;

import java.util.List;

public interface IQuestionRepository {
    List<Question> findAll();
    Question findById(long id);
    void save(Question question);
    void delete(Question question);
    void deleteById(long id);
    List<Question> findRandomBySubject(String subject_name, int n);
    boolean isPresent(long id);
}
