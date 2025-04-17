package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Model.Question;
import com.unicauca.sga.testService.Domain.Ports.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.QuestionJpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class QuestionRepository implements IQuestionRepository {
    private final QuestionJpaRepository questionJpaRepository;
    private final QuestionMapper questionMapper;

    public QuestionRepository (QuestionJpaRepository questionJpaRepository, QuestionMapper questionMapper){
        this.questionJpaRepository=questionJpaRepository;
        this.questionMapper=questionMapper;
    }

    @Override
    public List<Question> findAll() {
        return questionJpaRepository.findAll().stream().map(questionMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Question findById(long id) {
        return questionMapper.toModel(questionJpaRepository.findById(id).get());
    }

    @Override
    public void save(Question question) {
        questionJpaRepository.save(questionMapper.toInfra(question));
    }

    @Override
    public void delete(Question question) {
        questionJpaRepository.delete(questionMapper.toInfra(question));
    }

    @Override
    public void deleteById(long id) {
        questionJpaRepository.deleteById(id);
    }

    @Override
    public List<Question> findRandomBySubject(String subject_name, int n) {
        return questionJpaRepository.findRandomBySubject(subject_name, Pageable.ofSize(n)).stream().map(questionMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public boolean isPresent(long id) {
        return questionJpaRepository.existsById(id);
    }
}
