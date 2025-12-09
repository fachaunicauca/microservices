package com.unicauca.sga.testService.Domain.Repositories;

import com.unicauca.sga.testService.Domain.Model.QuestionTopic;

import java.util.List;

public interface IQuestionTopicRepository {
    List<QuestionTopic> getAllQuestionTopics();
    QuestionTopic getQuestionTopicById(int id);
    void save(QuestionTopic questionTopic);
    void delete(QuestionTopic questionTopic);
    void deleteById(int id);
    boolean isPresent(int id);
}
