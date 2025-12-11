package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.Answer;
import com.unicauca.sga.testService.Domain.Repositories.IAnswerRepository;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.AnswerMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.AnswerJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerRepository implements IAnswerRepository {

    private final AnswerJpaRepository answerJpaRepository;
    private final AnswerMapper answerMapper;

    private Answer toModel(AnswerTable answer) {
        if(answer == null) return null;
        return answerMapper.toModel(answer, new CycleAvoidingMappingContext());
    }

    private AnswerTable toInfra(Answer answer) {
        if(answer == null) return null;
        return answerMapper.toInfra(answer, new CycleAvoidingMappingContext());
    }

    @Override
    public void save(Answer answer) {
        answerJpaRepository.save(toInfra(answer));
    }

    @Override
    public void deleteById(long id) {
        answerJpaRepository.deleteById(id);
    }

    @Override
    public List<Answer> getQuestionAnswers(long questionId) {
        return answerJpaRepository.findByQuestion_QuestionId(questionId).stream().map(this::toModel).toList();
    }

    @Override
    public boolean isPresent(long id) {
        return answerJpaRepository.existsById(id);
    }
}
