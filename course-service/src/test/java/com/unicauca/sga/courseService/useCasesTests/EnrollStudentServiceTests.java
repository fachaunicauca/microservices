package com.unicauca.sga.courseService.useCasesTests;

import com.unicauca.sga.courseService.Application.UseCases.EnrollStudentService;
import com.unicauca.sga.courseService.Domain.Exceptions.AlreadyEnrolledException;
import com.unicauca.sga.courseService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.courseService.Domain.Models.Course;
import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Domain.Models.StudentEnrollment;
import com.unicauca.sga.courseService.Domain.Repositories.ICourseRepository;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentEnrollmentRepository;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentRepository;
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
class EnrollStudentServiceTests {

    @Mock private ICourseRepository courseRepository;
    @Mock private IStudentRepository studentRepository;
    @Mock private IStudentEnrollmentRepository studentEnrollmentRepository;

    @InjectMocks
    private EnrollStudentService enrollStudentService;

    private static final String STUDENT_EMAIL = "student@mail.com";
    private static final int COURSE_ID = 1;
    private static final long STUDENT_ID = 10L;

    private Student student;
    private Course course;
    private StudentEnrollment enrollment;

    @BeforeEach
    void setUp() {
        student = mock(Student.class);
        course = mock(Course.class);
        enrollment = mock(StudentEnrollment.class);
    }

    // ==================== getCourseStudents ====================

    @Test
    void getCourseStudents_shouldReturnStudents_whenCourseExistsAndHasStudents() {
        when(courseRepository.isPresent(COURSE_ID)).thenReturn(true);
        when(studentEnrollmentRepository.getCourseStudents(COURSE_ID, 0, 10)).thenReturn(List.of(student));

        Iterable<Student> result = enrollStudentService.getCourseStudents(COURSE_ID, 0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getCourseStudents_shouldThrowNotFound_whenCourseDoesNotExist() {
        when(courseRepository.isPresent(COURSE_ID)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> enrollStudentService.getCourseStudents(COURSE_ID, 0, 10));

        verify(studentEnrollmentRepository, never()).getCourseStudents(anyInt(), anyInt(), anyInt());
    }

    @Test
    void getCourseStudents_shouldThrowNotFound_whenCourseHasNoStudents() {
        when(courseRepository.isPresent(COURSE_ID)).thenReturn(true);
        when(studentEnrollmentRepository.getCourseStudents(COURSE_ID, 0, 10)).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> enrollStudentService.getCourseStudents(COURSE_ID, 0, 10));
    }

    // ==================== enrollStudent ====================

    @Test
    void enrollStudent_shouldEnrollStudent_whenStudentAndCourseExistAndIsNotAlreadyEnrolled() {
        when(studentRepository.getStudentByEmail(STUDENT_EMAIL)).thenReturn(Optional.of(student));
        when(student.getStudentId()).thenReturn(STUDENT_ID);
        when(courseRepository.getCourseById(COURSE_ID)).thenReturn(Optional.of(course));
        when(studentEnrollmentRepository.isStudentInCourse(STUDENT_ID, COURSE_ID)).thenReturn(false);
        when(studentEnrollmentRepository.saveStudentEnrollment(any(StudentEnrollment.class))).thenReturn(enrollment);

        StudentEnrollment result = enrollStudentService.enrollStudent(STUDENT_EMAIL, COURSE_ID);

        assertEquals(enrollment, result);
        verify(studentEnrollmentRepository).saveStudentEnrollment(any(StudentEnrollment.class));
    }

    @Test
    void enrollStudent_shouldThrowNotFound_whenStudentDoesNotExist() {
        when(studentRepository.getStudentByEmail(STUDENT_EMAIL)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> enrollStudentService.enrollStudent(STUDENT_EMAIL, COURSE_ID));

        verify(courseRepository, never()).getCourseById(anyInt());
        verify(studentEnrollmentRepository, never()).saveStudentEnrollment(any());
    }

    @Test
    void enrollStudent_shouldThrowNotFound_whenCourseDoesNotExist() {
        when(studentRepository.getStudentByEmail(STUDENT_EMAIL)).thenReturn(Optional.of(student));
        when(courseRepository.getCourseById(COURSE_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> enrollStudentService.enrollStudent(STUDENT_EMAIL, COURSE_ID));

        verify(studentEnrollmentRepository, never()).saveStudentEnrollment(any());
    }

    @Test
    void enrollStudent_shouldThrowAlreadyEnrolled_whenStudentIsAlreadyInCourse() {
        when(studentRepository.getStudentByEmail(STUDENT_EMAIL)).thenReturn(Optional.of(student));
        when(student.getStudentId()).thenReturn(STUDENT_ID);
        when(courseRepository.getCourseById(COURSE_ID)).thenReturn(Optional.of(course));
        when(studentEnrollmentRepository.isStudentInCourse(STUDENT_ID, COURSE_ID)).thenReturn(true);

        assertThrows(AlreadyEnrolledException.class, () -> enrollStudentService.enrollStudent(STUDENT_EMAIL, COURSE_ID));

        verify(studentEnrollmentRepository, never()).saveStudentEnrollment(any());
    }

    // ==================== unenrollStudent ====================

    @Test
    void unenrollStudent_shouldDeleteEnrollment_whenEnrollmentExists() {
        long enrollmentId = 5L;
        when(studentEnrollmentRepository.getStudentEnrollment(STUDENT_ID, COURSE_ID)).thenReturn(Optional.of(enrollment));
        when(enrollment.getStudentEnrollmentId()).thenReturn(enrollmentId);

        enrollStudentService.unenrollStudent(STUDENT_ID, COURSE_ID);

        verify(studentEnrollmentRepository).deleteById(enrollmentId);
    }

    @Test
    void unenrollStudent_shouldThrowNotFound_whenEnrollmentDoesNotExist() {
        when(studentEnrollmentRepository.getStudentEnrollment(STUDENT_ID, COURSE_ID)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> enrollStudentService.unenrollStudent(STUDENT_ID, COURSE_ID));

        verify(studentEnrollmentRepository, never()).deleteById(anyLong());
    }

    // ==================== isStudentInCourse ====================

    @Test
    void isStudentInCourse_shouldReturnTrue_whenStudentExistsAndIsEnrolled() {
        when(studentRepository.getStudentByEmail(STUDENT_EMAIL)).thenReturn(Optional.of(student));
        when(student.getStudentId()).thenReturn(STUDENT_ID);
        when(studentEnrollmentRepository.isStudentInCourse(STUDENT_ID, COURSE_ID)).thenReturn(true);

        boolean result = enrollStudentService.isStudentInCourse(STUDENT_EMAIL, COURSE_ID);

        assertTrue(result);
    }

    @Test
    void isStudentInCourse_shouldReturnFalse_whenStudentExistsButIsNotEnrolled() {
        when(studentRepository.getStudentByEmail(STUDENT_EMAIL)).thenReturn(Optional.of(student));
        when(student.getStudentId()).thenReturn(STUDENT_ID);
        when(studentEnrollmentRepository.isStudentInCourse(STUDENT_ID, COURSE_ID)).thenReturn(false);

        boolean result = enrollStudentService.isStudentInCourse(STUDENT_EMAIL, COURSE_ID);

        assertFalse(result);
    }

    @Test
    void isStudentInCourse_shouldReturnFalse_whenStudentDoesNotExist() {
        when(studentRepository.getStudentByEmail(STUDENT_EMAIL)).thenReturn(Optional.empty());

        boolean result = enrollStudentService.isStudentInCourse(STUDENT_EMAIL, COURSE_ID);

        assertFalse(result);
        verify(studentEnrollmentRepository, never()).isStudentInCourse(anyLong(), anyInt());
    }

    // ==================== getCourseStudentsEmails ====================

    @Test
    void getCourseStudentsEmails_shouldReturnEmails_whenCourseHasStudents() {
        List<String> emails = List.of("a@mail.com", "b@mail.com");
        when(studentEnrollmentRepository.getCourseStudentsEmails(COURSE_ID)).thenReturn(emails);

        List<String> result = enrollStudentService.getCourseStudentsEmails(COURSE_ID);

        assertEquals(2, result.size());
        assertEquals(emails, result);
    }

    @Test
    void getCourseStudentsEmails_shouldReturnEmptyList_whenCourseHasNoStudents() {
        when(studentEnrollmentRepository.getCourseStudentsEmails(COURSE_ID)).thenReturn(Collections.emptyList());

        List<String> result = enrollStudentService.getCourseStudentsEmails(COURSE_ID);

        assertTrue(result.isEmpty());
    }
}
