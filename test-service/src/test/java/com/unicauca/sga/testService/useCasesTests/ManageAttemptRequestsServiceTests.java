package com.unicauca.sga.testService.useCasesTests;

import com.unicauca.sga.testService.Aplication.UseCases.ManageAttemptRequestsService;
import com.unicauca.sga.testService.Domain.Enums.AttemptRequestStatus;
import com.unicauca.sga.testService.Domain.Exceptions.HasRemainingAttemptsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManageAttemptRequestsServiceTests {

    @Mock private IStudentTestConfigRepository studentTestConfigRepository;
    @Mock private ITestRepository testRepository;

    @InjectMocks
    private ManageAttemptRequestsService manageAttemptRequestsService;

    private static final String STUDENT_EMAIL = "student@mail.com";
    private StudentTestConfig studentTestConfig;

    @BeforeEach
    void setUp() {
        studentTestConfig = mock(StudentTestConfig.class);
    }

    // ==================== getPendingAttemptRequestPaged ====================

    @Test
    void getPendingAttemptRequestPaged_shouldReturnConfigs_whenTestExistsAndHasPendingRequests() {
        List<StudentTestConfig> configs = List.of(studentTestConfig);
        when(testRepository.isPresent(1)).thenReturn(true);
        when(studentTestConfigRepository.getConfigsWithPendingAttemptRequest(1, 0, 10)).thenReturn(configs);

        Iterable<StudentTestConfig> result = manageAttemptRequestsService.getPendingAttemptRequestPaged(1, 0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getPendingAttemptRequestPaged_shouldThrowNotFound_whenTestDoesNotExist() {
        when(testRepository.isPresent(99)).thenReturn(false);

        assertThrows(NotFoundException.class,
                () -> manageAttemptRequestsService.getPendingAttemptRequestPaged(99, 0, 10));

        verify(studentTestConfigRepository, never()).getConfigsWithPendingAttemptRequest(anyInt(), anyInt(), anyInt());
    }

    @Test
    void getPendingAttemptRequestPaged_shouldThrowNotFound_whenNoPendingRequestsExist() {
        when(testRepository.isPresent(1)).thenReturn(true);
        when(studentTestConfigRepository.getConfigsWithPendingAttemptRequest(1, 0, 10)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> manageAttemptRequestsService.getPendingAttemptRequestPaged(1, 0, 10));
    }

    // ==================== requestAttempts ====================

    @Test
    void requestAttempts_shouldMarkAsRequested_whenStudentHasNoRemainingAttempts() {
        when(studentTestConfigRepository.getStudentTestConfig(STUDENT_EMAIL, 1)).thenReturn(Optional.of(studentTestConfig));
        when(studentTestConfig.hasRemainingAttempts()).thenReturn(false);

        manageAttemptRequestsService.requestAttempts(STUDENT_EMAIL, 1);

        verify(studentTestConfig).setAttemptRequestStatus(AttemptRequestStatus.REQUESTED);
        verify(studentTestConfigRepository).save(studentTestConfig);
    }

    @Test
    void requestAttempts_shouldThrowNotFound_whenStudentHasNoConfig() {
        when(studentTestConfigRepository.getStudentTestConfig(STUDENT_EMAIL, 1)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> manageAttemptRequestsService.requestAttempts(STUDENT_EMAIL, 1));

        verify(studentTestConfigRepository, never()).save(any());
    }

    @Test
    void requestAttempts_shouldThrowHasRemainingAttempts_whenStudentStillHasAttempts() {
        when(studentTestConfigRepository.getStudentTestConfig(STUDENT_EMAIL, 1)).thenReturn(Optional.of(studentTestConfig));
        when(studentTestConfig.hasRemainingAttempts()).thenReturn(true);

        assertThrows(HasRemainingAttemptsException.class, () -> manageAttemptRequestsService.requestAttempts(STUDENT_EMAIL, 1));

        verify(studentTestConfig, never()).setAttemptRequestStatus(any());
        verify(studentTestConfigRepository, never()).save(any());
    }

    // ==================== resetAttempts ====================

    @Test
    void resetAttempts_shouldResetAttemptsAndClearRequest_whenConfigExists() {
        when(studentTestConfigRepository.getStudentTestConfig(STUDENT_EMAIL, 1))
                .thenReturn(Optional.of(studentTestConfig));

        manageAttemptRequestsService.resetAttempts(STUDENT_EMAIL, 1);

        InOrder order = inOrder(studentTestConfig, studentTestConfigRepository);
        order.verify(studentTestConfig).setAttemptRequestStatus(AttemptRequestStatus.NOT_REQUESTED);
        order.verify(studentTestConfig).setAttemptsUsed(0);
        order.verify(studentTestConfigRepository).save(studentTestConfig);
    }

    @Test
    void resetAttempts_shouldThrowNotFound_whenConfigDoesNotExist() {
        when(studentTestConfigRepository.getStudentTestConfig(STUDENT_EMAIL, 99)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> manageAttemptRequestsService.resetAttempts(STUDENT_EMAIL, 99));

        verify(studentTestConfigRepository, never()).save(any());
    }
}
