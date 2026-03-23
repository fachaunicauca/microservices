package com.unicauca.sga.testService.useCasesTests;

import com.unicauca.sga.testService.Aplication.Services.QuestionImageService;
import com.unicauca.sga.testService.Aplication.Services.QuestionStructureValidatorRegistry;
import com.unicauca.sga.testService.Aplication.UseCases.ManageQuestionsService;
import com.unicauca.sga.testService.Domain.Constants.TestConstants;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Domain.Services.IMoodleQuestionParser;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManageQuestionsServiceTests {

    @Mock private IQuestionRepository questionRepository;
    @Mock private QuestionImageService questionImageService;
    @Mock private QuestionStructureValidatorRegistry validatorRegistry;
    @Mock private ITestRepository testRepository;
    @Mock private IMoodleQuestionParser moodleQuestionParser;
    @Mock private QuestionStructureValidator structureValidator;

    @InjectMocks
    private ManageQuestionsService manageQuestionsService;

    private Question question;
    private com.unicauca.sga.testService.Domain.Models.Test test;

    @BeforeEach
    void setUp() {
        question = mock(Question.class);
    }

    // ==================== Pruebas metodo getTestQuestionsPaged ====================

    @Test
    void getTestQuestionsPaged_shouldReturnQuestions_whenTestHasQuestions() {
        List<Question> questions = List.of(question);
        when(questionRepository.getTestQuestionsPaged(1, 0, 10)).thenReturn(questions);

        Iterable<Question> result = manageQuestionsService.getTestQuestionsPaged(1, 0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getTestQuestionsPaged_shouldThrowNotFound_whenTestHasNoQuestions() {
        when(questionRepository.getTestQuestionsPaged(1, 0, 10)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> manageQuestionsService.getTestQuestionsPaged(1, 0, 10));
    }

    // ==================== Pruebas metodo saveQuestion ====================

    @Test
    void saveQuestion_shouldSaveQuestion_whenTestExists() {
        test = mock(com.unicauca.sga.testService.Domain.Models.Test.class);
        when(test.getTestId()).thenReturn(1);

        when(question.getTest()).thenReturn(test);
        when(question.getQuestionType()).thenReturn("MULTIPLE_CHOICE");
        when(question.getQuestionStructure()).thenReturn("{\"options\":[]}");

        when(testRepository.isPresent(1)).thenReturn(true);
        when(validatorRegistry.get("MULTIPLE_CHOICE")).thenReturn(structureValidator);
        when(structureValidator.validateStructure(any())).thenReturn("{\"options\":[]}");
        when(questionRepository.save(question)).thenReturn(question);

        Question result = manageQuestionsService.saveQuestion(question);

        assertNotNull(result);
        verify(questionImageService).syncQuestionImage(question);
        verify(questionRepository).save(question);
    }

    @Test
    void saveQuestion_shouldThrowNotFound_whenTestDoesNotExist() {
        test = mock(com.unicauca.sga.testService.Domain.Models.Test.class);
        when(test.getTestId()).thenReturn(1);

        when(question.getTest()).thenReturn(test);

        when(testRepository.isPresent(1)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> manageQuestionsService.saveQuestion(question));

        verify(questionRepository, never()).save(any());
    }

    // ==================== Pruebas metodo deleteQuestionById ====================

    @Test
    void deleteQuestionById_shouldDeleteQuestion_whenIdExists() {
        test = mock(com.unicauca.sga.testService.Domain.Models.Test.class);
        when(test.getTestId()).thenReturn(1);

        when(question.getTest()).thenReturn(test);

        when(questionRepository.getById(1L)).thenReturn(Optional.of(question));
        when(questionRepository.getTestTotalQuestions(1)).thenReturn(5L);
        when(test.hasEnoughQuestions(4L)).thenReturn(true);

        manageQuestionsService.deleteQuestionById(1L);

        verify(questionRepository).deleteById(1L);
        verify(questionImageService).cleanupQuestionImage(question);
    }

    @Test
    void deleteQuestionById_shouldThrowNotFound_whenIdDoesNotExist() {
        when(questionRepository.getById(99L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> manageQuestionsService.deleteQuestionById(99L));

        verify(questionRepository, never()).deleteById(anyLong());
    }

    @Test
    void deleteQuestionById_shouldDeactivateTest_whenTestEndsUpWithInsufficientQuestions() {
        test = mock(com.unicauca.sga.testService.Domain.Models.Test.class);
        when(test.getTestId()).thenReturn(1);

        when(question.getTest()).thenReturn(test);

        when(questionRepository.getById(1L)).thenReturn(Optional.of(question));
        when(questionRepository.getTestTotalQuestions(1)).thenReturn(1L);
        when(test.hasEnoughQuestions(0L)).thenReturn(false);
        when(test.isActive()).thenReturn(true);

        manageQuestionsService.deleteQuestionById(1L);

        verify(test).setTestState(TestConstants.INACTIVE);
        verify(testRepository).save(test);
    }

    // ==================== Pruebas metodo importQuestions ====================

    @Test
    void importQuestions_shouldThrowNotFound_whenTestDoesNotExist() {
        when(testRepository.isPresent(99)).thenReturn(false);

        assertThrows(NotFoundException.class,
                () -> manageQuestionsService.importQuestions(mock(InputStream.class), List.of(0), 99));

        verify(questionRepository, never()).saveAll(any());
    }

    @Test
    void importQuestions_shouldSaveQuestions_whenTestExists() {
        InputStream stream = mock(InputStream.class);
        List<Integer> indexes = List.of(0, 1);
        List<Question> parsed = List.of(question);

        when(testRepository.isPresent(1)).thenReturn(true);
        when(moodleQuestionParser.parseMoodleQuestions(stream, indexes, 1)).thenReturn(parsed);

        manageQuestionsService.importQuestions(stream, indexes, 1);

        verify(questionRepository).saveAll(parsed);
    }

    // ==================== Pruebas metodo exportQuestions ====================
    // Sin pruebas
}
