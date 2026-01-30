package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Strategy.Question.QuestionStrategyRegistry;
import com.unicauca.sga.testService.Domain.Constants.TestState;
import com.unicauca.sga.testService.Domain.Exceptions.ForbiddenOperationException;
import com.unicauca.sga.testService.Domain.Exceptions.InactiveTestException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStrategy;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestAttemptRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        Test generalTest = testRepository.getTestById(1).orElseThrow(() ->
                new NotFoundException("No se encontró la evaluación general")
        );

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

        Test test = testRepository.getTestById(testId).orElseThrow(() ->
                new NotFoundException("No se encontró la evaluación")
        );

        // Verificar que el test esté activo
        if (!test.isActive()) {
            throw new InactiveTestException("La evaluación no se encuentra activa");
        }

        // Verificar si ya se ha creado una configuración del estudiante y el test
        Optional<StudentTestConfig> existingConfig = studentTestConfigRepository.getStudentTestConfig(studentEmail, testId);

        StudentTestConfig config;

        if (existingConfig.isEmpty()) {
            // Si no se ha creado no es necesario realizar validaciones
            config = new StudentTestConfig();
            config.setStudentEmail(studentEmail);
            config.setTest(test);
            config.setAttemptLimit(test.getTestAttemptLimit());
            config.setAttemptsUsed(0);
            config.setFinalScore(null);
            config.setLastAttemptAt(null);

            studentTestConfigRepository.save(config);

        } else {
            // Si se ha creado es necesario realizar validaciones
            config = existingConfig.get();
            config.setTest(test);

            // Si el test es periódico y cambió el semestre, reiniciar intentos
            if (test.isPeriodic() && !config.isSameSemester()) {
                config.setAttemptsUsed(0);
                config.setFinalScore(null);
                config.setLastAttemptAt(null);
                studentTestConfigRepository.save(config);
            }

            // Verificar si ya aprobó
            if (config.hasAlreadyPassed(passingScore)) {
                throw new ForbiddenOperationException("El estudiante ya aprobó esta evaluación");
            }

            // Verificar intentos disponibles
            if (!config.hasRemainingAttempts()) {
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

    @Transactional
    public TestAttempt saveStudentTestAttempt(TestAttempt testAttempt){

        int testId = testAttempt.getTest().getTestId();
        String studentEmail = testAttempt.getStudentEmail();

        Test test  = testRepository.getTestById(testId).orElseThrow( () ->
                new NotFoundException("La evaluación ya no existe")
        );

        // Verificar que el studentTestConfig exista
        StudentTestConfig config = studentTestConfigRepository.getStudentTestConfig(studentEmail, testId).orElseThrow(() ->
                new ForbiddenOperationException("Debe iniciar un intento antes de poder guardarlo")
        );

        // Verificar nuevamente si tiene intentos disponibles
        if (!config.hasRemainingAttempts()) {
            throw new ForbiddenOperationException("El estudiante ya no puede presentar mas intentos en esta evaluación");
        }

        // Calificar las respuestas del estudiante
        long totalPoints = gradeStudentResponses(testAttempt);
        double score =(double) totalPoints / testAttempt.getTestAttemptNumberOfQuestions();
        testAttempt.setTestAttemptScore(score);

        LocalDateTime now = LocalDateTime.now();

        // Actualizar la configuración del estudiante y guardarlo
        config.incrementAttemptsUsed();
        config.setLastAttemptAt(now);
        config.setFinalScore(score);
        config.setTest(test);
        studentTestConfigRepository.save(config);

        // Guardar el intento
        testAttempt.setTestAttemptDate(now);
        testAttempt.setTest(test);
        testAttemptRepository.save(testAttempt);
        return testAttempt;
    }

    private List<Question> cleanQuestionStructures(List<Question> questions){
        questions.forEach(question -> {
            QuestionStrategy strategy = questionStrategyRegistry.get(question.getQuestionType());
            question.setQuestionStructure(strategy.cleanStructure(question.getQuestionStructure()));
        });
        return questions;
    }

    private long gradeStudentResponses(TestAttempt testAttempt) {
        // Obtener todos los questionIds de las respuestas
        Set<Long> questionIds = testAttempt.getStudentResponses().stream().map(StudentResponse::getQuestionId).collect(Collectors.toSet());

        // Traer todas las preguntas (optimización: una sola consulta)
        List<Question> questions = questionRepository.getByIds(questionIds);

        // Mapear preguntas por su ID
        Map<Long, Question> questionMap = questions.stream().collect(Collectors.toMap(Question::getQuestionId, Function.identity()));

        // Validar que todas las preguntas existan
        if (questionMap.size() != questionIds.size()) {
            Set<Long> foundIds = questionMap.keySet();

            Set<Long> missingIds = questionIds.stream().filter(id -> !foundIds.contains(id)).collect(Collectors.toSet());

            throw new NotFoundException("No se encontraron las preguntas con id: " + missingIds);
        }

        // Calificar respuestas
        long totalPoints = 0;
        boolean requiresManualGrading = false;
        for (StudentResponse response : testAttempt.getStudentResponses()) {
            Long questionId = response.getQuestionId();
            Question question = questionMap.get(questionId);

            QuestionStrategy strategy = questionStrategyRegistry.get(question.getQuestionType());

            if (strategy.requiresManualGrade()) {
                requiresManualGrading = true;
            } else {
                totalPoints += strategy.grade(question, response);
            }
        }

        testAttempt.setFullyScored(!requiresManualGrading);
        return totalPoints;
    }
}
