package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Services.QuestionImageService;
import com.unicauca.sga.testService.Aplication.Services.QuestionStructureHandlerRegistry;
import com.unicauca.sga.testService.Domain.Constants.TestState;
import com.unicauca.sga.testService.Domain.Exceptions.NoQuestionsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureHandler;
import com.unicauca.sga.testService.Domain.Models.Test;
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
    private final QuestionImageService questionImageService;
    private final QuestionStructureHandlerRegistry questionStructureHandlerRegistry;
    private final ITestRepository testRepository;

    @Transactional(readOnly = true)
    public Page<Question> getTestQuestionsPaged(int testid, Pageable pageable) {
        Page<Question> page = questionRepository.getTestQuestionsPaged(testid, pageable);

        if (page.isEmpty()) {
            throw new NoQuestionsException("La evaluación no tiene preguntas almacenadas.");
        }

        return page;
    }

    @Transactional
    public Question saveQuestion(Question question) {
        if(!testRepository.isPresent(question.getTest().getTestId())){
            throw new NotFoundException("No se encontró la evaluación a la que se quiere asignar la pregunta.");
        }

        questionImageService.syncQuestionImage(question);

        QuestionStructureHandler questionStructureHandler = questionStructureHandlerRegistry.get(question.getQuestionType());
        String validatedQuestionStructure = questionStructureHandler.validateStructure(question.getQuestionStructure());

        question.setQuestionStructure(validatedQuestionStructure);

        return questionRepository.save(question);
    }

    @Transactional
    public void deleteQuestionById(long questionId) {

        Question question = questionRepository.getById(questionId).orElseThrow(() ->
                new NotFoundException("No se encontró la pregunta con id: " + questionId)
        );

        Test test = question.getTest(); // Si existe la pregunta existe el test

        long totalQuestions = questionRepository.getTestTotalQuestions(test.getTestId());

        questionRepository.deleteById(questionId);

        // Si la pregunta tenia una imagen eliminarla del repositorio de archivos
        questionImageService.cleanupQuestionImage(question);

        // Validar que al eliminar la pregunta el test siga teniendo suficientes preguntas para estar activo
        // Si no las tiene desactivar el test
        if(!test.hasEnoughQuestions(totalQuestions - 1) && test.isActive()) {
            test.setTestState(TestState.INACTIVE);
            testRepository.save(test);
        }
    }


}
