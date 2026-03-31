package com.unicauca.sga.courseService.useCasesTests;

import com.unicauca.sga.courseService.Application.UseCases.ManageCoursesService;
import com.unicauca.sga.courseService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.courseService.Domain.Models.Course;
import com.unicauca.sga.courseService.Domain.Repositories.ICourseRepository;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentEnrollmentRepository;
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
class ManageCoursesServiceTests {

    @Mock private ICourseRepository courseRepository;
    @Mock private IStudentEnrollmentRepository studentEnrollmentRepository;

    @InjectMocks
    private ManageCoursesService manageCoursesService;

    private static final String TEACHER_EMAIL = "teacher@mail.com";
    private Course course;

    @BeforeEach
    void setUp() {
        course = mock(Course.class);
    }

    // ==================== getAllCoursesPaged ====================

    @Test
    void getAllCoursesPaged_shouldReturnCourses_whenCoursesExist() {
        when(courseRepository.getAllCoursesPagedAndFiltered("", "", 0, 10)).thenReturn(List.of(course));

        Iterable<Course> result = manageCoursesService.getAllCoursesPaged("", "", 0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getAllCoursesPaged_shouldThrowNotFound_whenCoursesListIsEmpty() {
        when(courseRepository.getAllCoursesPagedAndFiltered(any(), any(), anyInt(), anyInt())).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> manageCoursesService.getAllCoursesPaged("", "", 0, 10));
    }

    // ==================== getTeacherCoursesPaged ====================

    @Test
    void getTeacherCoursesPaged_shouldReturnCourses_whenTeacherHasCourses() {
        when(courseRepository.getTeacherCoursesPagedAndFiltered("", "", TEACHER_EMAIL, 0, 10))
                .thenReturn(List.of(course));

        Iterable<Course> result = manageCoursesService.getTeacherCoursesPaged("", "", TEACHER_EMAIL, 0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getTeacherCoursesPaged_shouldThrowNotFound_whenTeacherHasNoCourses() {
        when(courseRepository.getTeacherCoursesPagedAndFiltered(any(), any(), any(), anyInt(), anyInt()))
                .thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class,
                () -> manageCoursesService.getTeacherCoursesPaged("", "", TEACHER_EMAIL, 0, 10));
    }

    // ==================== getCourseById ====================

    @Test
    void getCourseById_shouldReturnCourse_whenCourseExists() {
        when(courseRepository.getCourseById(1)).thenReturn(Optional.of(course));

        Course result = manageCoursesService.getCourseById(1);

        assertEquals(course, result);
    }

    @Test
    void getCourseById_shouldThrowNotFound_whenCourseDoesNotExist() {
        when(courseRepository.getCourseById(99)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> manageCoursesService.getCourseById(99));
    }

    // ==================== saveCourse ====================

    @Test
    void saveCourse_shouldSaveCourse_whenCourseIdIsNull() {
        when(course.getCourseId()).thenReturn(null);
        when(courseRepository.saveCourse(course)).thenReturn(course);

        Course result = manageCoursesService.save(course);

        assertEquals(course, result);
        verify(courseRepository, never()).isPresent(anyInt());
        verify(courseRepository).saveCourse(course);
    }

    @Test
    void saveCourse_shouldUpdateCourse_whenCourseIdExistsInRepository() {
        when(course.getCourseId()).thenReturn(1);
        when(courseRepository.isPresent(1)).thenReturn(true);
        when(courseRepository.saveCourse(course)).thenReturn(course);

        Course result = manageCoursesService.save(course);

        assertEquals(course, result);
        verify(courseRepository).isPresent(1);
        verify(courseRepository).saveCourse(course);
    }

    @Test
    void saveCourse_shouldThrowNotFound_whenEditingCourseIdThatDoesNotExist() {
        when(course.getCourseId()).thenReturn(99);
        when(courseRepository.isPresent(99)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> manageCoursesService.save(course));

        verify(courseRepository, never()).saveCourse(any());
    }

    // ==================== deleteById ====================

    @Test
    void deleteById_shouldDeleteEnrollmentsAndCourse_whenCourseExists() {
        when(courseRepository.isPresent(1)).thenReturn(true);

        manageCoursesService.deleteById(1);

        InOrder order = inOrder(studentEnrollmentRepository, courseRepository);
        order.verify(studentEnrollmentRepository).deleteByCourseId(1);
        order.verify(courseRepository).deleteById(1);
    }

    @Test
    void deleteById_shouldThrowNotFound_whenCourseDoesNotExist() {
        when(courseRepository.isPresent(99)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> manageCoursesService.deleteById(99));

        verify(studentEnrollmentRepository, never()).deleteByCourseId(anyInt());
        verify(courseRepository, never()).deleteById(anyInt());
    }
}
