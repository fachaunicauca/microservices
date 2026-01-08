package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.QuestionJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionRepository implements IQuestionRepository {

    private final QuestionJpaRepository questionJpaRepository;
    private final QuestionMapper questionMapper;

    private Question toModel(QuestionEntity question){
        if(question == null) return null;
        return questionMapper.toModel(question, new CycleAvoidingMappingContext());
    }

    private QuestionEntity toInfra(Question question){
        if(question == null) return null;
        return questionMapper.toInfra(question, new CycleAvoidingMappingContext());
    }

    @Override
    public Question save(Question question) {
        return toModel(questionJpaRepository.save(toInfra(question)));
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
    public Question getById(long id) {
        return toModel(questionJpaRepository.findById(id).get());
    }

    @Override
    public List<Question> getRandomAndLimitedTestQuestions(int testId, int numberOfQuestions) {
        List<Long> questionIds = questionJpaRepository.findIdsByTestId(testId);
        Collections.shuffle(questionIds);

        List<Long> selectedIds = questionIds.subList(0, numberOfQuestions);

        List<QuestionEntity> entities = questionJpaRepository.findAllById(selectedIds);
        Collections.shuffle(entities);

        return entities.stream().map(this::toModel).toList();
    }

    @Override
    public Page<Question> getTestQuestionsPaged(int id, Pageable pageable) {
        return questionJpaRepository.findByTest_TestId(id, pageable).map(this::toModel);
    }

    @Override
    public long getTestTotalQuestions(int testId) {
        return questionJpaRepository.countByTest_TestId(testId);
    }
}
