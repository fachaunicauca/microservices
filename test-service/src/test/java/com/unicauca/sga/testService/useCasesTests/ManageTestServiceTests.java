package com.unicauca.sga.testService.useCasesTests;

import com.unicauca.sga.testService.Aplication.UseCases.ManageTestService;
import com.unicauca.sga.testService.Domain.Constants.TestConstants;
import com.unicauca.sga.testService.Domain.Exceptions.InsufficientQuestionsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Exceptions.ProtectedTestException;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Domain.Services.ICourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class ManageTestServiceTests {

    @Mock private ITestRepository testRepository;
    @Mock private IQuestionRepository questionRepository;
    @Mock private ICourseService courseService;
    @Mock private IStudentTestConfigRepository studentTestConfigRepository;

    @InjectMocks
    private ManageTestService manageTestService;

    private com.unicauca.sga.testService.Domain.Models.Test test;

    @BeforeEach
    void setUp() {
        test = mock(com.unicauca.sga.testService.Domain.Models.Test.class);
    }

    // ==================== Pruebas metodo getAllTests ====================

    @Test
    void getAllTests_shouldReturnTestsPage_whenTestsExist() {
        when(testRepository.getAllTestsFiltered("", "", 0, 10)).thenReturn(List.of(test));

        Iterable<com.unicauca.sga.testService.Domain.Models.Test> result = manageTestService.getAllTests("", "", 0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getAllTests_shouldThrowNotFound_whenNoTestsFound() {
        when(testRepository.getAllTestsFiltered("", "", 0, 10)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> manageTestService.getAllTests("", "", 0, 10));
    }

    // ==================== Pruebas metodo getAllTeacherTests ====================

    @Test
    void getAllTeacherTests_shouldReturnTestsPage_whenTeacherHasCreatedTests() {
        when(testRepository.getTeacherTestsFiltered("", "", "teacher@mail.com", 0, 10)).thenReturn(List.of(test));

        Iterable<com.unicauca.sga.testService.Domain.Models.Test> result = manageTestService.getAllTeacherTests("", "", "teacher@mail.com", 0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getAllTeacherTests_shouldThrowNotFound_whenTeacherHasNotCreatedTests() {
        when(testRepository.getTeacherTestsFiltered("", "", "teacher@mail.com", 0, 10)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class,
                () -> manageTestService.getAllTeacherTests("", "", "teacher@mail.com", 0, 10));
    }

    // ==================== Pruebas metodo getTestById ====================

    @Test
    void getTestById_shouldReturnTest_whenTestExists() {
        when(testRepository.getTestById(1)).thenReturn(Optional.of(test));

        com.unicauca.sga.testService.Domain.Models.Test result = manageTestService.getTestById(1);

        assertNotNull(result);
    }

    @Test
    void getTestById_shouldThrowNotFound_whenTestDoesNotExist() {
        when(testRepository.getTestById(99)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> manageTestService.getTestById(99));
    }

    // ==================== Pruebas metodo saveTest ====================

    @Test
    void saveTest_shouldSaveTest_whenCreatingNewTest() {
        when(test.getTestId()).thenReturn(null);
        when(test.isActive()).thenReturn(false);
        when(test.getCourseId()).thenReturn(0);
        when(testRepository.save(test)).thenReturn(test);

        com.unicauca.sga.testService.Domain.Models.Test result = manageTestService.saveTest(test);

        assertNotNull(result);
        verify(testRepository).save(test);
    }

    @Test
    void saveTest_shouldThrowNotFound_whenEditingNonExistentTest() {
        when(test.getTestId()).thenReturn(99);
        when(testRepository.isPresent(99)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> manageTestService.saveTest(test));

        verify(testRepository, never()).save(any());
    }

    @Test
    void saveTest_shouldThrowInsufficientQuestions_whenActivatingTestWithoutEnoughQuestions() {
        com.unicauca.sga.testService.Domain.Models.Test newTest = new com.unicauca.sga.testService.Domain.Models.Test();
        newTest.setTestId(1);
        newTest.setTestNumberOfQuestions(10); // Requiere como minimo 10 preguntas
        newTest.setTestState(TestConstants.ACTIVE); // Marcado como activo
        newTest.setCourseId(0);

        when(testRepository.isPresent(1)).thenReturn(true);
        when(questionRepository.getTestTotalQuestions(1)).thenReturn(4L); // Solo tiene 4 preguntas

        assertThrows(InsufficientQuestionsException.class, () -> manageTestService.saveTest(newTest));

        verify(testRepository, never()).save(any());
    }

    @Test
    void saveTest_shouldThrowNotFound_whenCourseDoesNotExist() {
        when(test.getTestId()).thenReturn(null);
        when(test.isActive()).thenReturn(false);
        when(test.getCourseId()).thenReturn(5);
        when(courseService.courseExistsById(5)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> manageTestService.saveTest(test));

        verify(testRepository, never()).save(any());
    }

    // ==================== Pruebas metodo deleteTestById ====================

    @Test
    void deleteTestById_shouldThrowProtectedTest_whenDeletingGeneralTest() {
        assertThrows(ProtectedTestException.class, () -> manageTestService.deleteTestById(1));

        verify(testRepository, never()).deleteById(anyInt());
    }

    @Test
    void deleteTestById_shouldThrowNotFoundException_whenTestDoesNotExist() {
        when(testRepository.isPresent(99)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> manageTestService.deleteTestById(99));

        verify(testRepository, never()).deleteById(anyInt());
    }

    @Test
    void deleteTestById_shouldDeleteTest_whenTestExists() {
        when(testRepository.isPresent(2)).thenReturn(true);

        manageTestService.deleteTestById(2);

        verify(studentTestConfigRepository).deleteAllByTestId(2);
        verify(testRepository).deleteById(2);
    }
}
