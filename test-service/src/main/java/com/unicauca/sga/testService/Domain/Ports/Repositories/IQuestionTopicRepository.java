package com.unicauca.sga.testService.Domain.Ports.Repositories;

import com.unicauca.sga.testService.Domain.Model.QuestionTopic;

import java.util.List;

public interface IQuestionTopicRepository {
    List<QuestionTopic> findAll();
    QuestionTopic findById(int id);
    void save(QuestionTopic questionTopic);
    void delete(QuestionTopic questionTopic);
    void deleteById(int id);
    boolean isPresent(int id);
}
