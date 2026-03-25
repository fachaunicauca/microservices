package com.unicauca.sga.testService.useCasesTests;

import com.unicauca.sga.testService.Aplication.Services.QuestionStructureGraderRegistry;
import com.unicauca.sga.testService.Aplication.UseCases.TakeTestService;
import com.unicauca.sga.testService.Domain.Constants.TestConstants;
import com.unicauca.sga.testService.Domain.Enums.AttemptNotAllowedCode;
import com.unicauca.sga.testService.Domain.Exceptions.AttemptNotAllowedException;
import com.unicauca.sga.testService.Domain.Exceptions.InactiveTestException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestAttemptRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Domain.Services.ICourseService;
import com.unicauca.sga.testService.Domain.Services.IDateTimeProvider;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureGrader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TakeTestServiceTests {

    @Mock private ITestRepository testRepository;
    @Mock private IQuestionRepository questionRepository;
    @Mock private ITestAttemptRepository testAttemptRepository;
    @Mock private IStudentTestConfigRepository studentTestConfigRepository;
    @Mock private QuestionStructureGraderRegistry questionStructureGraderRegistry;
    @Mock private ICourseService courseService;
    @Mock private QuestionStructureGrader grader;
    @Mock private IDateTimeProvider dateTimeProvider;

    @InjectMocks
    private TakeTestService takeTestService;

    private com.unicauca.sga.testService.Domain.Models.Test test;
    private StudentTestConfig config;
    private TestAttempt testAttempt;

    @BeforeEach
    void setUp() {
        test = mock(com.unicauca.sga.testService.Domain.Models.Test.class);
        config = mock(StudentTestConfig.class);
        testAttempt = mock(TestAttempt.class);
    }

    // ==================== getGeneralTest ====================

    @Test
    void getGeneralTest_shouldReturnTest_whenTestExistsAndIsActive() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.getTestState()).thenReturn(TestConstants.ACTIVE);

        com.unicauca.sga.testService.Domain.Models.Test result = takeTestService.getGeneralTest();

        assertNotNull(result);
    }

    @Test
    void getGeneralTest_shouldThrowNotFound_whenTestDoesNotExist() {
        when(testRepository.getTestById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> takeTestService.getGeneralTest());
    }

    @Test
    void getGeneralTest_shouldThrowInactiveTest_whenTestIsInactive() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.getTestState()).thenReturn(TestConstants.INACTIVE);

        assertThrows(InactiveTestException.class, () -> takeTestService.getGeneralTest());
    }

    // ==================== getAllActiveTests ====================

    @Test
    void getAllActiveTests_shouldReturnTests_whenThereAreActiveTests() {
        List<com.unicauca.sga.testService.Domain.Models.Test> tests = List.of(test);
        when(testRepository.getAllActiveTestsFiltered("", "", 0, 10))
                .thenReturn(tests);

        Iterable<com.unicauca.sga.testService.Domain.Models.Test> result =
                takeTestService.getAllActiveTests("", "", 0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getAllActiveTests_shouldThrowNotFound_whenListIsEmpty() {
        when(testRepository.getAllActiveTestsFiltered(any(), any(), anyInt(), anyInt()))
                .thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class,
                () -> takeTestService.getAllActiveTests("", "", 0, 10));
    }

    // ==================== getStudentTestAttempts ====================

    @Test
    void getStudentTestAttempts_shouldReturnAttempts_whenStudentHasAttempts() {
        List<TestAttempt> attempts = List.of(testAttempt);
        when(testAttemptRepository.getAllStudentTestAttempts("student@mail.com", 1))
                .thenReturn(attempts);

        List<TestAttempt> result = takeTestService.getStudentTestAttempts("student@mail.com", 1);

        assertFalse(result.isEmpty());
    }

    @Test
    void getStudentTestAttempts_shouldThrowNotFound_whenStudentHasNoAttempts() {
        when(testAttemptRepository.getAllStudentTestAttempts("student@mail.com", 1))
                .thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class,
                () -> takeTestService.getStudentTestAttempts("student@mail.com", 1));
    }

    // ==================== startTestAttempt ====================

    @Test
    void startTestAttempt_shouldThrowNotFound_whenTestDoesNotExist() {
        when(testRepository.getTestById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> takeTestService.startTestAttempt("student@mail.com", 1));
    }

    @Test
    void startTestAttempt_shouldThrowInactiveTest_whenTestIsInactive() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.isActive()).thenReturn(false);

        assertThrows(InactiveTestException.class,
                () -> takeTestService.startTestAttempt("student@mail.com", 1));
    }

    @Test
    void startTestAttempt_shouldThrowAttemptNotAllowed_whenStudentNotEnrolledInCourse() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.isActive()).thenReturn(true);
        when(test.getCourseId()).thenReturn(5);
        when(courseService.isStudentInCourse("student@mail.com", 5)).thenReturn(false);

        assertThrows(AttemptNotAllowedException.class,
                () -> takeTestService.startTestAttempt("student@mail.com", 1));
    }

    @Test
    void startTestAttempt_shouldNotCheckEnrollment_whenCourseIdIsZero() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.isActive()).thenReturn(true);
        when(test.getCourseId()).thenReturn(0);
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1)).thenReturn(Optional.empty());

        Question question = mock(Question.class);
        when(question.getQuestionType()).thenReturn("MULTIPLE_CHOICE");
        when(questionRepository.getRandomAndLimitedTestQuestions(anyInt(), anyInt())).thenReturn(List.of(question));
        when(questionStructureGraderRegistry.get("MULTIPLE_CHOICE")).thenReturn(grader);
        when(grader.cleanStructure(any())).thenReturn("{}");
        when(test.toStudentView(any())).thenReturn(test);

        assertDoesNotThrow(() -> takeTestService.startTestAttempt("student@mail.com", 1));
        verify(courseService, never()).isStudentInCourse(any(), anyInt());
    }

    @Test
    void startTestAttempt_shouldCreateNewConfig_whenStudentHasNoPreviousConfig() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.isActive()).thenReturn(true);
        when(test.getCourseId()).thenReturn(0);
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1))
                .thenReturn(Optional.empty());

        Question question = mock(Question.class);
        when(question.getQuestionType()).thenReturn("MULTIPLE_CHOICE");
        when(questionRepository.getRandomAndLimitedTestQuestions(anyInt(), anyInt()))
                .thenReturn(List.of(question));
        when(questionStructureGraderRegistry.get("MULTIPLE_CHOICE")).thenReturn(grader);
        when(grader.cleanStructure(any())).thenReturn("{}");
        when(test.toStudentView(any())).thenReturn(test);

        takeTestService.startTestAttempt("student@mail.com", 1);

        verify(studentTestConfigRepository).save(any(StudentTestConfig.class));
    }

    @Test
    void startTestAttempt_shouldThrowAttemptNotAllowed_whenStudentAlreadyPassed() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.isActive()).thenReturn(true);
        when(test.getCourseId()).thenReturn(0);
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1))
                .thenReturn(Optional.of(config));
        when(config.hasAlreadyPassed(TestConstants.PASSING_SCORE)).thenReturn(true);

        AttemptNotAllowedException ex = assertThrows(AttemptNotAllowedException.class,
                () -> takeTestService.startTestAttempt("student@mail.com", 1));

        assertEquals(AttemptNotAllowedCode.ALREADY_PASSED.toString(), ex.getCode());
    }

    @Test
    void startTestAttempt_shouldThrowAttemptNotAllowed_whenNoRemainingAttempts() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.isActive()).thenReturn(true);
        when(test.getCourseId()).thenReturn(0);
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1)).thenReturn(Optional.of(config));
        when(config.hasAlreadyPassed(TestConstants.PASSING_SCORE)).thenReturn(false);
        when(config.hasRemainingAttempts()).thenReturn(false);

        AttemptNotAllowedException ex = assertThrows(AttemptNotAllowedException.class,
                () -> takeTestService.startTestAttempt("student@mail.com", 1));

        assertEquals(AttemptNotAllowedCode.NO_REMAINING_ATTEMPTS.toString(), ex.getCode());
    }

    @Test
    void startTestAttempt_shouldResetAttemptsAndScore_whenTestIsPeriodicAndNewSemester() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.isActive()).thenReturn(true);
        when(test.getCourseId()).thenReturn(0);
        when(test.isPeriodic()).thenReturn(true);
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1))
                .thenReturn(Optional.of(config));

        LocalDateTime dateOfLastAttempt = LocalDateTime.of(2026, 1, 1, 0, 0);

        when(config.getLastAttemptAt()).thenReturn(dateOfLastAttempt);
        // false: semestre distinto
        when(dateTimeProvider.isInCurrentSemester(dateOfLastAttempt)).thenReturn(false);
        when(config.hasAlreadyPassed(TestConstants.PASSING_SCORE)).thenReturn(false);
        when(config.hasRemainingAttempts()).thenReturn(true);

        Question question = mock(Question.class);
        when(question.getQuestionType()).thenReturn("MULTIPLE_CHOICE");
        when(questionRepository.getRandomAndLimitedTestQuestions(anyInt(), anyInt())).thenReturn(List.of(question));
        when(questionStructureGraderRegistry.get("MULTIPLE_CHOICE")).thenReturn(grader);
        when(grader.cleanStructure(any())).thenReturn("{}");
        when(test.toStudentView(any())).thenReturn(test);

        takeTestService.startTestAttempt("student@mail.com", 1);

        verify(config).setAttemptsUsed(0);
        verify(config).setFinalScore(null);
        verify(studentTestConfigRepository, atLeastOnce()).save(config);
    }

    @Test
    void startTestAttempt_shouldNotResetAttempts_whenTestIsPeriodicButSameSemester() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(test.isActive()).thenReturn(true);
        when(test.getCourseId()).thenReturn(0);
        when(test.isPeriodic()).thenReturn(true);
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1)).thenReturn(Optional.of(config));

        // true: mismo semestre
        when(dateTimeProvider.isInCurrentSemester(config.getLastAttemptAt())).thenReturn(true);

        when(config.hasAlreadyPassed(TestConstants.PASSING_SCORE)).thenReturn(false);
        when(config.hasRemainingAttempts()).thenReturn(true);

        Question question = mock(Question.class);
        when(question.getQuestionType()).thenReturn("MULTIPLE_CHOICE");
        when(questionRepository.getRandomAndLimitedTestQuestions(anyInt(), anyInt())).thenReturn(List.of(question));
        when(questionStructureGraderRegistry.get("MULTIPLE_CHOICE")).thenReturn(grader);
        when(grader.cleanStructure(any())).thenReturn("{}");
        when(test.toStudentView(any())).thenReturn(test);

        takeTestService.startTestAttempt("student@mail.com", 1);

        verify(config, never()).setAttemptsUsed(0);
        verify(config, never()).setFinalScore(null);
    }

    // ==================== saveStudentTestAttempt ====================

    @Test
    void saveStudentTestAttempt_shouldThrowNotFound_whenTestNoLongerExists() {
        when(testAttempt.getTestId()).thenReturn(1);
        when(testAttempt.getStudentEmail()).thenReturn("student@mail.com");
        when(testRepository.getTestById(1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> takeTestService.saveStudentTestAttempt(testAttempt));

        verify(testAttemptRepository, never()).save(any());
    }

    @Test
    void saveStudentTestAttempt_shouldThrowAttemptNotAllowed_whenConfigDoesNotExist() {
        // Caso importante: estudiante intenta guardar sin haber iniciado el intento
        when(testAttempt.getTestId()).thenReturn(1);
        when(testAttempt.getStudentEmail()).thenReturn("student@mail.com");
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1)).thenReturn(Optional.empty());

        AttemptNotAllowedException ex = assertThrows(AttemptNotAllowedException.class,
                () -> takeTestService.saveStudentTestAttempt(testAttempt));

        assertEquals(AttemptNotAllowedCode.ATTEMPT_NOT_STARTED.toString(), ex.getCode());
        verify(testAttemptRepository, never()).save(any());
    }

    @Test
    void saveStudentTestAttempt_shouldThrowAttemptNotAllowed_whenNoRemainingAttemptsOnSave() {
        when(testAttempt.getTestId()).thenReturn(1);
        when(testAttempt.getStudentEmail()).thenReturn("student@mail.com");
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1)).thenReturn(Optional.of(config));
        when(config.hasRemainingAttempts()).thenReturn(false);

        AttemptNotAllowedException ex = assertThrows(AttemptNotAllowedException.class,
                () -> takeTestService.saveStudentTestAttempt(testAttempt));

        assertEquals(AttemptNotAllowedCode.NO_REMAINING_ATTEMPTS.toString(), ex.getCode());
        verify(testAttemptRepository, never()).save(any());
    }

    @Test
    void saveStudentTestAttempt_shouldScoreZero_whenResponsesListIsEmpty() {
        when(testAttempt.getTestId()).thenReturn(1);
        when(testAttempt.getStudentEmail()).thenReturn("student@mail.com");
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1)).thenReturn(Optional.of(config));
        when(config.hasRemainingAttempts()).thenReturn(true);
        when(testAttempt.getStudentResponses()).thenReturn(Collections.emptyList());
        when(testAttempt.getTestAttemptNumberOfQuestions()).thenReturn(5);

        takeTestService.saveStudentTestAttempt(testAttempt);

        // score = 0 / 5 = 0.0
        verify(testAttempt).setTestAttemptScore(0.0);
        verify(testAttemptRepository).save(testAttempt);
    }

    @Test
    void saveStudentTestAttempt_shouldThrowNotFound_whenQuestionIdInResponseNotFound() {
        StudentResponse response = mock(StudentResponse.class);
        when(response.getQuestionId()).thenReturn(99L);

        when(testAttempt.getTestId()).thenReturn(1);
        when(testAttempt.getStudentEmail()).thenReturn("student@mail.com");
        when(testAttempt.getStudentResponses()).thenReturn(List.of(response));
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1))
                .thenReturn(Optional.of(config));
        when(config.hasRemainingAttempts()).thenReturn(true);

        when(questionRepository.getByIds(Set.of(99L))).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class,
                () -> takeTestService.saveStudentTestAttempt(testAttempt));

        verify(testAttemptRepository, never()).save(any());
    }

    @Test
    void saveStudentTestAttempt_shouldSetFullyScoredFalse_whenAnyQuestionRequiresManualGrading() {
        Question question = mock(Question.class);
        when(question.getQuestionId()).thenReturn(1L);
        when(question.getQuestionType()).thenReturn("OPEN_ENDED");

        StudentResponse response = mock(StudentResponse.class);
        when(response.getQuestionId()).thenReturn(1L);

        when(testAttempt.getTestId()).thenReturn(1);
        when(testAttempt.getStudentEmail()).thenReturn("student@mail.com");
        when(testAttempt.getStudentResponses()).thenReturn(List.of(response));
        when(testAttempt.getTestAttemptNumberOfQuestions()).thenReturn(1);
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1))
                .thenReturn(Optional.of(config));
        when(config.hasRemainingAttempts()).thenReturn(true);
        when(questionRepository.getByIds(any())).thenReturn(List.of(question));
        when(questionStructureGraderRegistry.get("OPEN_ENDED")).thenReturn(grader);
        when(grader.requiresManualGrade()).thenReturn(true);

        takeTestService.saveStudentTestAttempt(testAttempt);

        // El intento NO debería marcarse como completamente calificado
        verify(testAttempt).setFullyScored(false);
    }

    @Test
    void saveStudentTestAttempt_shouldCalculateScoreCorrectly_withMultipleResponses() {
        Question q1 = mock(Question.class);
        Question q2 = mock(Question.class);
        when(q1.getQuestionId()).thenReturn(1L);
        when(q2.getQuestionId()).thenReturn(2L);
        when(q1.getQuestionType()).thenReturn("MULTIPLE_CHOICE");
        when(q2.getQuestionType()).thenReturn("MULTIPLE_CHOICE");

        StudentResponse r1 = mock(StudentResponse.class);
        StudentResponse r2 = mock(StudentResponse.class);
        when(r1.getQuestionId()).thenReturn(1L);
        when(r2.getQuestionId()).thenReturn(2L);

        when(testAttempt.getTestId()).thenReturn(1);
        when(testAttempt.getStudentEmail()).thenReturn("student@mail.com");
        when(testAttempt.getStudentResponses()).thenReturn(List.of(r1, r2));
        when(testAttempt.getTestAttemptNumberOfQuestions()).thenReturn(2);
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getStudentTestConfig("student@mail.com", 1))
                .thenReturn(Optional.of(config));
        when(config.hasRemainingAttempts()).thenReturn(true);
        when(questionRepository.getByIds(any())).thenReturn(List.of(q1, q2));
        when(questionStructureGraderRegistry.get("MULTIPLE_CHOICE")).thenReturn(grader);
        when(grader.requiresManualGrade()).thenReturn(false);
        // q1 correcta (1 punto), q2 incorrecta (0 puntos)
        when(grader.grade(eq(q1), eq(r1))).thenReturn(1);
        when(grader.grade(eq(q2), eq(r2))).thenReturn(0);

        takeTestService.saveStudentTestAttempt(testAttempt);

        // score = 1 / 2 = 0.5
        verify(testAttempt).setTestAttemptScore(0.5);
    }
}
