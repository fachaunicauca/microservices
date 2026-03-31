package com.unicauca.sga.courseService.useCasesTests;

import com.unicauca.sga.courseService.Application.UseCases.ManageStudentsService;
import com.unicauca.sga.courseService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentEnrollmentRepository;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ManageStudentsServiceTests {

    @Mock private IStudentRepository studentRepository;
    @Mock private IStudentEnrollmentRepository studentEnrollmentRepository;

    @InjectMocks
    private ManageStudentsService manageStudentsService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = mock(Student.class);
    }

    // ==================== getAllStudentsPaged ====================

    @Test
    void getAllStudentsPaged_shouldReturnStudents_whenStudentsExist() {
        when(studentRepository.getAllStudentsPaged(0, 10)).thenReturn(List.of(student));

        Iterable<Student> result = manageStudentsService.getAllStudentsPaged(0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getAllStudentsPaged_shouldThrowNotFound_whenStudentsListIsEmpty() {
        when(studentRepository.getAllStudentsPaged(anyInt(), anyInt())).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> manageStudentsService.getAllStudentsPaged(0, 10));
    }

    // ==================== saveStudent ====================

    @Test
    void saveStudent_shouldSaveStudent_whenStudentIdIsNull() {
        when(student.getStudentId()).thenReturn(null);
        when(studentRepository.saveStudent(student)).thenReturn(student);

        Student result = manageStudentsService.saveStudent(student);

        assertEquals(student, result);
        verify(studentRepository, never()).isPresent(anyLong());
        verify(studentRepository).saveStudent(student);
    }

    @Test
    void saveStudent_shouldUpdateStudent_whenStudentIdExistsInRepository() {
        when(student.getStudentId()).thenReturn(1L);
        when(studentRepository.isPresent(1L)).thenReturn(true);
        when(studentRepository.saveStudent(student)).thenReturn(student);

        Student result = manageStudentsService.saveStudent(student);

        assertEquals(student, result);
        verify(studentRepository).isPresent(1L);
        verify(studentRepository).saveStudent(student);
    }

    @Test
    void saveStudent_shouldThrowNotFound_whenEditingStudentIdThatDoesNotExist() {
        when(student.getStudentId()).thenReturn(99L);
        when(studentRepository.isPresent(99L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> manageStudentsService.saveStudent(student));

        verify(studentRepository, never()).saveStudent(any());
    }

    // ==================== deleteStudentById ====================

    @Test
    void deleteStudentById_shouldDeleteEnrollmentsAndStudent_whenStudentExists() {
        when(studentRepository.isPresent(1L)).thenReturn(true);

        manageStudentsService.deleteStudentById(1L);

        InOrder order = inOrder(studentEnrollmentRepository, studentRepository);
        order.verify(studentEnrollmentRepository).deleteByStudentId(1L);
        order.verify(studentRepository).deleteById(1L);
    }

    @Test
    void deleteStudentById_shouldThrowNotFound_whenStudentDoesNotExist() {
        when(studentRepository.isPresent(99L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> manageStudentsService.deleteStudentById(99L));

        verify(studentEnrollmentRepository, never()).deleteByStudentId(anyLong());
        verify(studentRepository, never()).deleteById(anyLong());
    }

    // ==================== getStudentsByEmails ====================

    @Test
    void getStudentsByEmails_shouldReturnStudents_whenEmailsMatch() {
        List<String> emails = List.of("student1@mail.com", "student2@mail.com");
        when(studentRepository.getAllStudentsByEmails(emails)).thenReturn(List.of(student));

        List<Student> result = manageStudentsService.getStudentsByEmails(emails);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void getStudentsByEmails_shouldReturnEmptyList_whenNoEmailsMatch() {
        List<String> emails = List.of("student1@mail.com");
        when(studentRepository.getAllStudentsByEmails(emails)).thenReturn(Collections.emptyList());

        List<Student> result = manageStudentsService.getStudentsByEmails(emails);

        assertTrue(result.isEmpty());
    }
}
