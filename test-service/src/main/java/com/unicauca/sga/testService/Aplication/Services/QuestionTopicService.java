package com.unicauca.sga.testService.Aplication.Services;

import com.unicauca.sga.testService.Domain.Model.QuestionTopic;
import com.unicauca.sga.testService.Domain.Ports.Repositories.IQuestionTopicRepository;
import com.unicauca.sga.testService.Domain.Ports.Services.IQuestionTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTopicService implements IQuestionTopicService {

    private final IQuestionTopicRepository questionTopicRepository;

    public QuestionTopicService(IQuestionTopicRepository questionTopicRepository) {
        this.questionTopicRepository = questionTopicRepository;
    }

    @Override
    public List<QuestionTopic> getAllQuestionTopics(){
        return (List<QuestionTopic>) questionTopicRepository.findAll();
    }

    @Override
    public QuestionTopic getQuestionTopicById(int id){
        return questionTopicRepository.findById(id);
    }

    @Override
    public void saveQuestionTopic(QuestionTopic questionTopic){
        questionTopicRepository.save(questionTopic);
    }

    @Override
    public void deleteQuestionTopic(QuestionTopic questionTopic){
        questionTopicRepository.delete(questionTopic);
    }

    @Override
    public void deleteQuestionTopicById(int id){
        questionTopicRepository.deleteById(id);
    }

    @Override
    public void updateQuestionTopic(QuestionTopic questionTopic){
        questionTopicRepository.save(questionTopic);
    }

    @Override
    public boolean isPresent(int id) {
        return questionTopicRepository.isPresent(id);
    }
}
