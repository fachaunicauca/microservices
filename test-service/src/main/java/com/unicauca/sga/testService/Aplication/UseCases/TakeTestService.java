package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Strategy.Question.QuestionStrategyRegistry;
import com.unicauca.sga.testService.Domain.Constants.TestState;
import com.unicauca.sga.testService.Domain.Exceptions.ForbiddenOperationException;
import com.unicauca.sga.testService.Domain.Exceptions.InactiveTestException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStrategy;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestAttemptRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.TakeTestDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TakeTestService {

    private final ITestRepository testRepository;
    private final IQuestionRepository questionRepository;
    private final ITestAttemptRepository testAttemptRepository;
    private final IStudentTestConfigRepository studentTestConfigRepository;
    private final QuestionStrategyRegistry questionStrategyRegistry;

    private final double passingScore = 0.6;

    @Transactional(readOnly = true)
    public Test getGeneralTest(){
        if(!testRepository.isPresent(1)){
            throw new NotFoundException("No se encontró la evaluación general");
        }

        Test generalTest = testRepository.getTestById(1);

        if(generalTest.getTestState() == TestState.INACTIVE){
            throw new InactiveTestException("La evaluación general se encuentra inactiva");
        }

        return generalTest;
    }

    @Transactional(readOnly = true)
    public Page<Test> getAllActiveTests(Pageable pageable){
        Page<Test> activeTests = testRepository.getAllActiveTests(pageable);

        if(activeTests.getTotalElements() == 0){
            throw new NotFoundException("No se encontraron evaluaciones especificas activas");
        }

        return activeTests;
    }

    @Transactional
    public Test startTestAttempt(String studentEmail, int testId) {

        // Verificar que el test exista
        if (!testRepository.isPresent(testId)) {
            throw new NotFoundException("No se encontró la evaluación");
        }

        Test test = testRepository.getTestById(testId);

        // Verificar que el test esté activo
        if (test.getTestState() == TestState.INACTIVE) {
            throw new InactiveTestException("La evaluación se encuentra inactiva");
        }

        // Obtener o crear la configuración del estudiante para el test
        StudentTestConfig config;

        // Si no existe la configuración crearla (No hay validaciones)
        if (!studentTestConfigRepository.isPresent(studentEmail, testId)) {

            config = new StudentTestConfig();
            config.setStudentEmail(studentEmail);
            config.setTest(test);
            config.setAttemptLimit(test.getTestAttemptLimit());
            config.setAttemptsUsed(0);
            config.setFinalScore(null);
            config.setLastAttemptAt(null);

            studentTestConfigRepository.save(config);

        } else {
            // Si existe la configuración obtenerla y verificar si el estudiante puede presentar la evaluación
            config = studentTestConfigRepository.getStudentTestConfig(studentEmail, testId);
            config.setTest(test);

            // Si el test es periódico y cambió el semestre reiniciar los intentos
            if (test.isPeriodic() && !config.isSameSemester()) {
                config.setAttemptsUsed(0);
                config.setFinalScore(null);
                config.setLastAttemptAt(null);
                studentTestConfigRepository.save(config);
            }

            // Verificar si ya ha aprobado el test
            if (config.hasAlreadyPassed(passingScore)) {
                throw new ForbiddenOperationException("El estudiante ya aprobó esta evaluación");
            }

            // Verificar si tiene intentos disponibles
            if (config.getAttemptsUsed() >= config.getAttemptLimit()) {
                throw new ForbiddenOperationException("El estudiante no tiene intentos disponibles para esta evaluación");
            }
        }

        // Si el test está activo significa que tiene la suficiente cantidad de preguntas
        List<Question> testQuestions = questionRepository.getRandomAndLimitedTestQuestions(testId, test.getTestNumberOfQuestions());

        // Construir Test con los campos necesarios para presentar la evaluación
        Test studentTest = new Test();
        studentTest.setTestId(test.getTestId());
        studentTest.setTestTitle(test.getTestTitle());
        studentTest.setTestDurationMinutes(test.getTestDurationMinutes());
        studentTest.setTestNumberOfQuestions(test.getTestNumberOfQuestions());
        studentTest.setQuestions(cleanQuestionStructures(testQuestions));

        return studentTest;
    }

    private List<Question> cleanQuestionStructures(List<Question> questions){
        questions.forEach(question -> {
            QuestionStrategy strategy = questionStrategyRegistry.get(question.getQuestionType());
            question.setQuestionStructure(strategy.cleanStructure(question.getQuestionStructure()));
        });
        return questions;
    }
}
