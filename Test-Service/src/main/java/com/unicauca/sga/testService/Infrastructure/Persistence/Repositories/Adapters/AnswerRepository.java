package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Model.Answer;
import com.unicauca.sga.testService.Domain.Ports.Repositories.IAnswerRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.AnswerMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.AnswerJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class AnswerRepository implements IAnswerRepository {
    private final AnswerJpaRepository answerJpaRepository;
    private final AnswerMapper answerMapper;

    public AnswerRepository (AnswerJpaRepository answerJpaRepository, AnswerMapper answerMapper){
        this.answerJpaRepository=answerJpaRepository;
        this.answerMapper=answerMapper;
    }

    @Override
    public List<Answer> findAll() {
        return answerJpaRepository.findAll().stream().map(answerMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Answer findById(long id) {
        return answerMapper.toModel(answerJpaRepository.findById(id).get());
    }

    @Override
    public void save(Answer answer) {
        answerJpaRepository.save(answerMapper.toInfra(answer));
    }

    @Override
    public void delete(Answer answer) {
        answerJpaRepository.delete(answerMapper.toInfra(answer));
    }

    @Override
    public void deleteById(long id) {
        answerJpaRepository.deleteById(id);
    }

    @Override
    public List<Answer> findByQuestionId(Long question_id) {
        return answerJpaRepository.findByQuestionId(question_id).stream().map(answerMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public boolean isCorrect(long id) {
        return answerJpaRepository.isCorrect(id);
    }

    @Override
    public boolean isPresent(long id) {
        return answerJpaRepository.existsById(id);
    }
}
