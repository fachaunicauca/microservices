package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Model.Question;
import com.unicauca.sga.testService.Domain.Ports.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.QuestionJpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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
        List<Question> questions = questionJpaRepository
                .findBySubjectSubjectName(subject_name)
                .stream()
                .map(questionMapper::toModel)
                .collect(Collectors.toList());

        // Mezclar la lista en memoria
        Collections.shuffle(questions);

        // Retornar solo los primeros n elementos
        if (questions.size() > n) {
            return questions.subList(0, n);
        } else {
            return questions;
        }
    }

    @Override
    public boolean isPresent(long id) {
        return questionJpaRepository.existsById(id);
    }
}
