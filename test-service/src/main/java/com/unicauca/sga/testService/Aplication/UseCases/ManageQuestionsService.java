package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Strategy.Question.QuestionStrategyRegistry;
import com.unicauca.sga.testService.Domain.Exceptions.NoQuestionsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStrategy;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManageQuestionsService {

    private final IQuestionRepository questionRepository;
    private final QuestionStrategyRegistry questionStrategyRegistry;
    private final ITestRepository testRepository;

    @Transactional(readOnly = true)
    public Page<Question> getTestQuestionsPaged(int testid, Pageable pageable) {
        if(questionRepository.getTestTotalQuestions(testid) <= 0){
            throw new NoQuestionsException("La evaluación no tiene preguntas almacenadas.");
        }

        return questionRepository.getTestQuestionsPaged(testid, pageable);
    }

    @Transactional
    public Question saveQuestion(Question question) {
        if(!testRepository.isPresent(question.getTest().getTestId())){
            throw new NotFoundException("No se encontró la evaluación a la que se quiere asignar la pregunta.");
        }

        QuestionStrategy questionStrategy = questionStrategyRegistry.get(question.getQuestionType());
        String validatedQuestionStructure = questionStrategy.validateStructure(question.getQuestionStructure());

        question.setQuestionStructure(validatedQuestionStructure);

        return questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestionById(long questionId) {
        if(!questionRepository.isPresent(questionId)) {
            throw new NotFoundException("No se encontró la pregunta con id: " + questionId);
        }

        questionRepository.deleteById(questionId);
    }


}
