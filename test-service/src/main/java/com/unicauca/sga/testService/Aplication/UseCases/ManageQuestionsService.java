package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Services.QuestionImageService;
import com.unicauca.sga.testService.Aplication.Services.QuestionStructureValidatorRegistry;
import com.unicauca.sga.testService.Domain.Constants.TestConstants;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Domain.Services.IMoodleQuestionParser;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageQuestionsService {

    private final IQuestionRepository questionRepository;
    private final QuestionImageService questionImageService;
    private final QuestionStructureValidatorRegistry questionStructureValidatorRegistry;
    private final ITestRepository testRepository;
    private final IMoodleQuestionParser moodleQuestionParser;

    @Transactional(readOnly = true)
    public Iterable<Question> getTestQuestionsPaged(int testId, int page, int size) {
        Iterable<Question> questionsPaged = questionRepository.getTestQuestionsPaged(testId, page, size);

        if (!questionsPaged.iterator().hasNext()) {
            throw new NotFoundException("La evaluación no tiene preguntas almacenadas.");
        }

        return questionsPaged;
    }

    @Transactional
    public Question saveQuestion(Question question) {
        if(!testRepository.isPresent(question.getTest().getTestId())){
            throw new NotFoundException("No se encontró la evaluación a la que se quiere asignar la pregunta.");
        }

        questionImageService.syncQuestionImage(question);

        QuestionStructureValidator questionStructureHandler = questionStructureValidatorRegistry.get(question.getQuestionType());
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
            test.setTestState(TestConstants.INACTIVE);
            testRepository.save(test);
        }
    }

    public void importQuestions (InputStream fileStream, List<Integer> selectedIndexes, int testId){
        if(!testRepository.isPresent(testId)){
            throw new NotFoundException("No se encontró la evaluación a la que se quieren agregar las preguntas");
        }

        List<Question> importedQuestions = moodleQuestionParser.parseMoodleQuestions(fileStream, selectedIndexes, testId);

        questionRepository.saveAll(importedQuestions);
    }
}
