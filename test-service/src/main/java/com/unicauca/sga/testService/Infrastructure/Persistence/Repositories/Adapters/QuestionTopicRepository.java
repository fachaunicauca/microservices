package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.OldVersion.QuestionTopic;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionTopicRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionTopicMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.QuestionTopicJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionTopicRepository implements IQuestionTopicRepository {
    private final QuestionTopicJpaRepository questionTopicJpaRepository;
    private final QuestionTopicMapper questionTopicMapper;

    public QuestionTopicRepository (QuestionTopicJpaRepository questionTopicJpaRepository, QuestionTopicMapper questionTopicMapper){
        this.questionTopicJpaRepository=questionTopicJpaRepository;
        this.questionTopicMapper=questionTopicMapper;
    }

    @Override
    public List<QuestionTopic> getAllQuestionTopics() {
        return questionTopicJpaRepository.findAll().stream().map(questionTopicMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public QuestionTopic getQuestionTopicById(int id) {
        return questionTopicMapper.toModel(questionTopicJpaRepository.findById(id).get());
    }

    @Override
    public void save(QuestionTopic questionTopic) {
        questionTopicJpaRepository.save(questionTopicMapper.toInfra(questionTopic));
    }

    @Override
    public void delete(QuestionTopic questionTopic) {
        questionTopicJpaRepository.delete(questionTopicMapper.toInfra(questionTopic));
    }

    @Override
    public void deleteById(int id) {
        questionTopicJpaRepository.deleteById(id);
    }

    @Override
    public boolean isPresent(int id) {
        return questionTopicJpaRepository.existsById(id);
    }
}
