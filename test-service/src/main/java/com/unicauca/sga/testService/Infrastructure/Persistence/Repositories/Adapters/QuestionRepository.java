package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.QuestionJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QuestionRepository implements IQuestionRepository {

    private final QuestionJpaRepository questionJpaRepository;
    private final QuestionMapper questionMapper;

    @Override
    public Question save(Question question) {
        return questionMapper.toModel(questionJpaRepository.save(questionMapper.toInfra(question)));
    }

    @Override
    public void deleteById(long id) {
        questionJpaRepository.deleteById(id);
    }


    @Override
    public Optional<Question> getById(long id) {
        return questionJpaRepository.findById(id).map(questionMapper::toModel);
    }

    @Override
    public List<Question> getRandomAndLimitedTestQuestions(int testId, int numberOfQuestions) {
        List<Long> questionIds = questionJpaRepository.findIdsByTestId(testId);
        Collections.shuffle(questionIds);

        List<Long> selectedIds = questionIds.subList(0, numberOfQuestions);

        List<QuestionEntity> entities = questionJpaRepository.findAllById(selectedIds);
        Collections.shuffle(entities);

        return entities.stream().map(questionMapper::toModelWithoutTest).toList();
    }

    @Override
    public Page<Question> getTestQuestionsPaged(int id, Pageable pageable) {
        return questionJpaRepository.findByTest_TestIdOrderByQuestionId(id, pageable).map(questionMapper::toModelWithoutTest);
    }

    @Override
    public long getTestTotalQuestions(int testId) {
        return questionJpaRepository.countByTest_TestId(testId);
    }
}
