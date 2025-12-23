package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Domain.Exceptions.NoQuestionsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageQuestionsService {

    private final QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public Page<Question> getTestQuestionsPaged(int testid, Pageable pageable) {
        if(questionRepository.getTestTotalQuestions(testid) <= 0){
            throw new NoQuestionsException("La evaluación no tiene preguntas almacenadas.");
        }

        return questionRepository.getTestQuestionsPaged(testid, pageable);
    }

    @Transactional
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestion(long questionId) {
        if(!questionRepository.isPresent(questionId)) {
            throw new NotFoundException("No se encontró la pregunta con id: " + questionId);
        }

        questionRepository.deleteById(questionId);
    }


}
