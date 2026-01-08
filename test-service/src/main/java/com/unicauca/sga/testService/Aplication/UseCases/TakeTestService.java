package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Strategy.Question.QuestionStrategyRegistry;
import com.unicauca.sga.testService.Domain.Constants.TestState;
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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<Question> startTestAttempt(String studentEmail, int testId){

        // 1. Verificar que el test exista
        if (!testRepository.isPresent(testId)) {
            throw new NotFoundException("No se encontró la evaluación");
        }

        Test test = testRepository.getTestById(testId);

        // 2. Verificar que el test esté activo
        if (test.getTestState() == TestState.INACTIVE) {
            throw new InactiveTestException("La evaluación se encuentra inactiva");
        }

        // 3. Verificar si ya existe una configuración del estudiante para el test
        boolean configExists = studentTestConfigRepository.isPresent(studentEmail, testId);

        StudentTestConfig config;

        // 4. Si NO existe una configuración previa es la primera vez que va a presentar el test
        if (!configExists) {

            config = new StudentTestConfig();
            config.setStudentEmail(studentEmail);
            config.setTest(test);
            config.setAttemptLimit(test.getTestAttemptLimit());
            config.setAttemptsUsed(0);
            config.setFinalScore(null);
            config.setLastAttemptAt(null);

            studentTestConfigRepository.save(config);

            return cleanQuestionStructures(questionRepository.getRandomAndLimitedTestQuestions(testId, test.getTestNumberOfQuestions()));
        }

        // 5. Ya existe una configuración
        config = studentTestConfigRepository.getStudentTestConfig(studentEmail, testId);

        // 6. Si el test es periódico y cambió el semestre reiniciar los intentos
        if (test.isPeriodic() && !config.isSameSemester()) {

            config.setAttemptsUsed(0);
            config.setFinalScore(null);
            config.setLastAttemptAt(null);

            studentTestConfigRepository.save(config);
        }

        // 7. Verificar si ya aprobó el test en el semestre actual
        // Nota: Aquí se podría pasar el passingScore que tenga el objeto Test
        if (config.hasPassedCurrentSemester(passingScore)) {
            throw new IllegalStateException(
                    "El estudiante ya aprobó esta evaluación en el semestre actual"
            );
        }

        // 8. Verificar si tiene intentos disponibles
        if (config.getAttemptsUsed() >= config.getAttemptLimit()) {
            throw new IllegalStateException(
                    "El estudiante no tiene intentos disponibles para esta evaluación"
            );
        }

        // 9. Si se cumplieron todas las validaciones retornar las preguntas
        return cleanQuestionStructures(questionRepository.getRandomAndLimitedTestQuestions(testId, test.getTestNumberOfQuestions()));
    }

    private List<Question> cleanQuestionStructures(List<Question> questions){
        questions.forEach(question -> {
            QuestionStrategy strategy = questionStrategyRegistry.get(question.getQuestionType());
            question.setQuestionStructure(strategy.cleanStructure(question.getQuestionStructure()));
        });
        return questions;
    }
}
