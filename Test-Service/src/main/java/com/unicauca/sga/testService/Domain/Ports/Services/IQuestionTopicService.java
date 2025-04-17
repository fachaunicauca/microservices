package com.unicauca.sga.testService.Domain.Ports.Services;

import com.unicauca.sga.testService.Domain.Model.QuestionTopic;

import java.util.List;

public interface IQuestionTopicService {
    List<QuestionTopic> getAllQuestionTopics();
    QuestionTopic getQuestionTopicById(int id);
    void saveQuestionTopic(QuestionTopic questionTopic);
    void deleteQuestionTopic(QuestionTopic questionTopic);
    void deleteQuestionTopicById(int id);
    void updateQuestionTopic(QuestionTopic questionTopic);
    boolean isPresent(int id);

}
