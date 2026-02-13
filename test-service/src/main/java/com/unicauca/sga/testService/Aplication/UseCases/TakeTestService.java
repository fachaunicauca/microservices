package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Services.ICourseService;
import com.unicauca.sga.testService.Aplication.Services.QuestionStructureHandlerRegistry;
import com.unicauca.sga.testService.Domain.Constants.TestState;
import com.unicauca.sga.testService.Domain.Enums.AttemptNotAllowedCode;
import com.unicauca.sga.testService.Domain.Exceptions.AttemptNotAllowedException;
import com.unicauca.sga.testService.Domain.Exceptions.InactiveTestException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureHandler;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestAttemptRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import lombok.RequiredArgsConstructor;
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
    private final QuestionStructureHandlerRegistry questionStructureHandlerRegistry;
    private final ICourseService courseService;

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
    public Iterable<Test> getAllActiveTests(int page, int size){
        Iterable<Test> activeTests = testRepository.getAllActiveTests(page, size);

        if(!activeTests.iterator().hasNext()){
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

        // Verificar que el estudiante este asignado al curso al que pertenece el test
        // Si la evaluación está marcada con el courseId = 0 cualquier estudiante puede presentarla
        if(test.getCourseId() != 0 && !courseService.isStudentInCourse(studentEmail, test.getCourseId())){
            throw new AttemptNotAllowedException(AttemptNotAllowedCode.STUDENT_NOT_ENROLLED,
                                                    "No estas inscrito en el curso al que pertenece esta evaluación");
        }

        // Verificar si ya se ha creado una configuración del estudiante y el test
        Optional<StudentTestConfig> existingConfig = studentTestConfigRepository.getStudentTestConfig(studentEmail, testId);

        StudentTestConfig config;

        if (existingConfig.isEmpty()) {
            // Si no se ha creado no es necesario realizar validaciones
            config = new StudentTestConfig(studentEmail, test);
            studentTestConfigRepository.save(config);

        } else {
            // Si se ha creado es necesario realizar validaciones
            config = existingConfig.get();

            // Si el test es periódico y cambió el semestre, reiniciar intentos
            if (test.isPeriodic() && !config.isSameSemester()) {
                config.setAttemptsUsed(0);
                config.setFinalScore(null);
                studentTestConfigRepository.save(config);
            }

            // Verificar si ya aprobó
            if (config.hasAlreadyPassed(passingScore)) {
                throw new AttemptNotAllowedException(AttemptNotAllowedCode.ALREADY_PASSED,
                                                    "Ya has aprobado esta evaluación");
            }

            // Verificar intentos disponibles
            if (!config.hasRemainingAttempts()) {
                throw new AttemptNotAllowedException(AttemptNotAllowedCode.NO_REMAINING_ATTEMPTS ,
                                                    "No tienes intentos disponibles para esta evaluación");
            }
        }

        // Si el test está activo significa que tiene la suficiente cantidad de preguntas
        List<Question> testQuestions = questionRepository.getRandomAndLimitedTestQuestions(testId, test.getTestNumberOfQuestions());

        // Construir Test solo con los campos necesarios para presentar la evaluación
        return test.toStudentView(cleanQuestionStructures(testQuestions));
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
                new AttemptNotAllowedException(AttemptNotAllowedCode.ATTEMPT_NOT_STARTED ,
                                                "Debe iniciar un intento antes de poder guardarlo")
        );

        // Verificar nuevamente si tiene intentos disponibles
        if (!config.hasRemainingAttempts()) {
            throw new AttemptNotAllowedException(AttemptNotAllowedCode.NO_REMAINING_ATTEMPTS ,
                    "Te has quedado sin intentos en esta evaluación");
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
            QuestionStructureHandler strategy = questionStructureHandlerRegistry.get(question.getQuestionType());
            question.setQuestionStructure(strategy.cleanStructure(question.getQuestionStructure()));
        });
        return questions;
    }

    private long gradeStudentResponses(TestAttempt testAttempt) {
        // Si la lista de respuestas es nula o esta vacía devolver 0
        if(testAttempt.getStudentResponses() == null || testAttempt.getStudentResponses().isEmpty()){
            return 0;
        }

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

            QuestionStructureHandler strategy = questionStructureHandlerRegistry.get(question.getQuestionType());

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
