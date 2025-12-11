package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.Question;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.QuestionJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository implements IQuestionRepository {

    private final QuestionJpaRepository questionJpaRepository;
    private final QuestionMapper questionMapper;

    private Question toModel(QuestionTable question){
        if(question == null) return null;
        return questionMapper.toModel(question, new CycleAvoidingMappingContext());
    }

    private QuestionTable toInfra(Question question){
        if(question == null) return null;
        return questionMapper.toInfra(question, new CycleAvoidingMappingContext());
    }

    @Override
    public void save(Question question) {
        questionJpaRepository.save(toInfra(question));
    }

    @Override
    public void deleteById(long id) {
        questionJpaRepository.deleteById(id);
    }

    @Override
    public boolean isPresent(long id) {
        return questionJpaRepository.existsById(id);
    }

    @Override
    public List<Question> getRandomAndLimitedTestQuestions(int testId, int numberOfQuestions) {
        return questionJpaRepository.findByTest_TestId(testId, Limit.of(numberOfQuestions)).stream().map(this::toModel).toList();
    }

    @Override
    public List<Question> getTestQuestions(int id) {
        return questionJpaRepository.findByTest_TestId(id).stream().map(this::toModel).toList();
    }
}
