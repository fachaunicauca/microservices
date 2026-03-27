package com.unicauca.sga.testService.useCasesTests;

import com.unicauca.sga.testService.Aplication.UseCases.GenerateTestResultsService;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Models.TestResults.StudentTestResult;
import com.unicauca.sga.testService.Domain.Models.TestResults.TestStats;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Domain.Services.ICourseService;
import com.unicauca.sga.testService.Domain.Services.IStudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenerateTestResultsServiceTests {

    @Mock private ICourseService courseService;
    @Mock private IStudentTestConfigRepository studentTestConfigRepository;
    @Mock private ITestRepository testRepository;
    @Mock private IStudentService studentService;

    @InjectMocks
    private GenerateTestResultsService generateTestResultsService;

    private static final int TEST_ID = 1;
    private static final int COURSE_ID = 10;
    private static final String STUDENT_EMAIL = "student@mail.com";
    private com.unicauca.sga.testService.Domain.Models.Test test;

    @BeforeEach
    void setUp(){
        test = mock(com.unicauca.sga.testService.Domain.Models.Test.class);
    }

    // ==================== getTestResultsPaged ====================

    @Test
    void getTestResultsPaged_shouldThrowNotFound_whenTestDoesNotExist() {
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10));
    }

    @Test
    void getTestResultsPaged_shouldDelegateToCoursePath_whenTestHasCourse() {
        when(test.getCourseId()).thenReturn(COURSE_ID);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));

        StudentTestResult studentResult = mock(StudentTestResult.class);
        when(studentResult.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        List<StudentTestResult> students = List.of(studentResult);
        when(courseService.getCourseStudents(COURSE_ID, 0, 10)).thenReturn(students);
        when(studentTestConfigRepository.getAllByTestIdAndStudentEmailIn(eq(TEST_ID), any()))
                .thenReturn(Collections.emptyList());

        Iterable<StudentTestResult> result =
                generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10);

        assertTrue(result.iterator().hasNext());
        verify(courseService).getCourseStudents(COURSE_ID, 0, 10);
    }

    @Test
    void getTestResultsPaged_shouldDelegateToGlobalPath_whenTestHasNoCourse() {
        when(test.getCourseId()).thenReturn(0);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));

        StudentTestResult studentResult = mock(StudentTestResult.class);
        when(studentResult.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        List<StudentTestResult> results = List.of(studentResult);
        when(studentTestConfigRepository.getAllResultsByTestId(TEST_ID, 0, 10)).thenReturn(results);
        when(studentService.getStudentsByEmails(any())).thenReturn(Collections.emptyList());

        Iterable<StudentTestResult> result =
                generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10);

        assertTrue(result.iterator().hasNext());
        verify(studentTestConfigRepository).getAllResultsByTestId(TEST_ID, 0, 10);
    }

    // ==================== getTestResultsPaged (caso con curso) ====================

    @Test
    void getTestResultsPaged_shouldThrowNotFound_whenCourseHasNoStudents() {
        when(test.getCourseId()).thenReturn(COURSE_ID);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));
        when(courseService.getCourseStudents(COURSE_ID, 0, 10)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10));

        verify(studentTestConfigRepository, never()).getAllByTestIdAndStudentEmailIn(anyInt(), any());
    }

    @Test
    void getTestResultsPaged_shouldAssignScoreFromConfig_whenConfigExists() {
        when(test.getCourseId()).thenReturn(COURSE_ID);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));

        StudentTestResult studentResult = mock(StudentTestResult.class);
        when(studentResult.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        when(courseService.getCourseStudents(COURSE_ID, 0, 10)).thenReturn(List.of(studentResult));

        StudentTestConfig config = mock(StudentTestConfig.class);
        when(config.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        when(config.getFinalScore()).thenReturn(0.5);
        when(config.getTotalAttemptsUsed()).thenReturn(2);
        when(studentTestConfigRepository.getAllByTestIdAndStudentEmailIn(eq(TEST_ID), any())).thenReturn(List.of(config));

        generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10);

        verify(studentResult).setFinalScore(0.5);
        verify(studentResult).setTotalAttemptsUsed(2);
    }

    @Test
    void getTestResultsPaged_shouldAssignScoreZero_whenConfigDoesNotExistForStudent() {
        when(test.getCourseId()).thenReturn(COURSE_ID);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));

        StudentTestResult studentResult = mock(StudentTestResult.class);
        when(studentResult.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        when(courseService.getCourseStudents(COURSE_ID, 0, 10)).thenReturn(List.of(studentResult));
        when(studentTestConfigRepository.getAllByTestIdAndStudentEmailIn(eq(TEST_ID), any())).thenReturn(Collections.emptyList());

        generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10);

        verify(studentResult).setFinalScore(0.0);
        verify(studentResult).setTotalAttemptsUsed(0);
    }

    @Test
    void getTestResultsPaged_shouldAssignScoreZero_whenConfigFinalScoreIsNull() {
        when(test.getCourseId()).thenReturn(COURSE_ID);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));

        StudentTestResult studentResult = mock(StudentTestResult.class);
        when(studentResult.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        when(courseService.getCourseStudents(COURSE_ID, 0, 10)).thenReturn(List.of(studentResult));

        StudentTestConfig config = mock(StudentTestConfig.class);
        when(config.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        when(config.getFinalScore()).thenReturn(null);
        when(config.getTotalAttemptsUsed()).thenReturn(1);
        when(studentTestConfigRepository.getAllByTestIdAndStudentEmailIn(eq(TEST_ID), any())).thenReturn(List.of(config));

        generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10);

        verify(studentResult).setFinalScore(0.0);
    }

    // ==================== getTestResultsPaged (caso sin curso) ====================

    @Test
    void getTestResultsPaged_shouldThrowNotFound_whenNoStudentHasTakenAnyGeneralTest() {
        when(test.getCourseId()).thenReturn(0);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getAllResultsByTestId(TEST_ID, 0, 10)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10));
    }

    @Test
    void getTestResultsPaged_shouldAssignStudentInfo_whenStudentInfoExists() {
        when(test.getCourseId()).thenReturn(0);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));

        StudentTestResult result = mock(StudentTestResult.class);
        when(result.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        when(studentTestConfigRepository.getAllResultsByTestId(TEST_ID, 0, 10))
                .thenReturn(List.of(result));

        StudentTestResult info = mock(StudentTestResult.class);
        when(info.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        when(info.getStudentId()).thenReturn(42L);
        when(info.getStudentFirstName()).thenReturn("Nombre");
        when(info.getStudentLastName()).thenReturn("Apellido");
        when(studentService.getStudentsByEmails(any())).thenReturn(List.of(info));

        generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10);

        verify(result).setStudentId(42L);
        verify(result).setStudentFirstName("Nombre");
        verify(result).setStudentLastName("Apellido");
    }

    @Test
    void getTestResultsPaged_shouldAssignPlaceholderInfo_whenStudentInfoNotFound() {
        when(test.getCourseId()).thenReturn(0);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));

        StudentTestResult result = mock(StudentTestResult.class);
        when(result.getStudentEmail()).thenReturn(STUDENT_EMAIL);
        when(studentTestConfigRepository.getAllResultsByTestId(TEST_ID, 0, 10))
                .thenReturn(List.of(result));
        when(studentService.getStudentsByEmails(any())).thenReturn(Collections.emptyList());

        generateTestResultsService.getTestResultsPaged(TEST_ID, 0, 10);

        verify(result).setStudentId(longThat(id -> id < 0));
        verify(result).setStudentFirstName("");
        verify(result).setStudentLastName("");
    }

    // ==================== getTestStats ====================

    @Test
    void getTestStats_shouldThrowNotFound_whenTestDoesNotExist() {
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> generateTestResultsService.getTestStats(TEST_ID));
    }

    @Test
    void getTestStats_shouldReturnStatsWithNullNotTaken_whenTestHasNoCourse() {
        when(test.getCourseId()).thenReturn(0);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getTestScoresByTestId(TEST_ID)).thenReturn(List.of(0.1, 0.8, 1.0));

        TestStats stats = generateTestResultsService.getTestStats(TEST_ID);

        assertNull(stats.getTotalNotTaken());
        assertEquals(3, stats.getTotalTaken());
        verify(courseService, never()).getCourseStudentsEmails(anyInt());
    }

    @Test
    void getTestStats_shouldCalculateNotTaken_whenTestHasCourse() {
        when(test.getCourseId()).thenReturn(COURSE_ID);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));
        when(courseService.getCourseStudentsEmails(COURSE_ID)).thenReturn(List.of("a@mail.com", "b@mail.com", "c@mail.com"));
        when(studentTestConfigRepository.getTestScoresByEmails(eq(TEST_ID), any())).thenReturn(List.of(0.7, 0.9));

        TestStats stats = generateTestResultsService.getTestStats(TEST_ID);

        assertEquals(2, stats.getTotalTaken());
        assertEquals(1, stats.getTotalNotTaken());
    }

    @Test
    void getTestStats_shouldCalculatePassedAndFailed_correctly() {
        when(test.getCourseId()).thenReturn(0);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getTestScoresByTestId(TEST_ID))
                .thenReturn(List.of(0.4, 0.7, 0.9));

        TestStats stats = generateTestResultsService.getTestStats(TEST_ID);

        assertEquals(3, stats.getTotalTaken());
        assertEquals(2, stats.getTotalPassed());
        assertEquals(1, stats.getTotalFailed());
    }

    @Test
    void getTestStats_shouldReturnZeroAverage_whenNoScoresExist() {
        when(test.getCourseId()).thenReturn(0);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getTestScoresByTestId(TEST_ID))
                .thenReturn(Collections.emptyList());

        TestStats stats = generateTestResultsService.getTestStats(TEST_ID);

        assertEquals(0, stats.getTotalTaken());
        assertEquals(0.0, stats.getAverageScore());
        assertEquals(0.0, stats.getStandardDeviation());
    }

    @Test
    void getTestStats_shouldCalculateAverageAndStdDev_correctly() {
        when(test.getCourseId()).thenReturn(0);
        when(testRepository.getTestById(TEST_ID)).thenReturn(Optional.of(test));
        when(studentTestConfigRepository.getTestScoresByTestId(TEST_ID)).thenReturn(List.of(0.6, 0.8, 1.0));

        TestStats stats = generateTestResultsService.getTestStats(TEST_ID);

        assertEquals(0.8, stats.getAverageScore(), 0.001);
        assertEquals(Math.sqrt(0.08 / 3.0), stats.getStandardDeviation(), 0.001);
    }
}