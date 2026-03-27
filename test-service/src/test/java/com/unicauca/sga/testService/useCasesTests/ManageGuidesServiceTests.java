package com.unicauca.sga.testService.useCasesTests;

import com.unicauca.sga.testService.Aplication.UseCases.ManageGuidesService;
import com.unicauca.sga.testService.Domain.Exceptions.AlreadyExistsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.TestGuide;
import com.unicauca.sga.testService.Domain.Repositories.IFilesRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestGuidesRepository;
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
class ManageGuidesServiceTests {

    @Mock private ITestGuidesRepository testGuidesRepository;
    @Mock private IFilesRepository filesRepository;

    @InjectMocks
    private ManageGuidesService manageGuidesService;

    private static final String TEACHER_EMAIL = "teacher@mail.com";
    private TestGuide testGuide;

    @BeforeEach
    void setUp() {
        testGuide = mock(TestGuide.class);
    }

    // ==================== saveTestGuide ====================

    @Test
    void saveTestGuide_shouldSaveGuide_whenIdIsNew() {
        when(testGuide.getTestGuideId()).thenReturn("guia_matematicas");
        when(testGuide.getTestGuideArchive()).thenReturn("contenido de prueba".getBytes());
        when(testGuide.getTeacherEmail()).thenReturn(TEACHER_EMAIL);
        when(testGuidesRepository.isPresent("guia_matematicas")).thenReturn(false);
        when(filesRepository.uploadFile(any(), eq("guia_matematicas"))).thenReturn("https://files.com/guia_matematicas");
        when(testGuidesRepository.save(any(TestGuide.class))).thenAnswer(inv -> inv.getArgument(0));

        TestGuide result = manageGuidesService.saveTestGuide(testGuide);

        assertEquals("guia_matematicas", result.getTestGuideId());
        assertEquals("https://files.com/guia_matematicas", result.getTestGuideUrl());
        assertEquals(TEACHER_EMAIL, result.getTeacherEmail());
    }

    @Test
    void saveTestGuide_shouldReplaceSpacesWithUnderscores_beforeCheckingAndSaving() {
        when(testGuide.getTestGuideId()).thenReturn("guia matematicas");
        when(testGuide.getTestGuideArchive()).thenReturn("contenido de prueba".getBytes());
        when(testGuide.getTeacherEmail()).thenReturn(TEACHER_EMAIL);
        when(testGuidesRepository.isPresent("guia_matematicas")).thenReturn(false);
        when(filesRepository.uploadFile(any(), eq("guia_matematicas"))).thenReturn("https://files.com/guia_matematicas");
        when(testGuidesRepository.save(any(TestGuide.class))).thenAnswer(inv -> inv.getArgument(0));

        TestGuide result = manageGuidesService.saveTestGuide(testGuide);

        assertEquals("guia_matematicas", result.getTestGuideId());
        verify(testGuidesRepository).isPresent("guia_matematicas");
        verify(filesRepository).uploadFile(any(), eq("guia_matematicas"));
    }

    @Test
    void saveTestGuide_shouldThrowAlreadyExists_whenGuideIdExists() {
        when(testGuide.getTestGuideId()).thenReturn("guia_matematicas");
        when(testGuidesRepository.isPresent("guia_matematicas")).thenReturn(true);

        assertThrows(AlreadyExistsException.class, () -> manageGuidesService.saveTestGuide(testGuide));

        verify(filesRepository, never()).uploadFile(any(), any());
        verify(testGuidesRepository, never()).save(any());
    }

    // ==================== getAllTestGuides ====================

    @Test
    void getAllTestGuides_shouldReturnGuides_whenGuidesExist() {
        when(testGuidesRepository.getAllTestsGuidesFiltered("", "", 0, 10)).thenReturn(List.of(testGuide));

        Iterable<TestGuide> result = manageGuidesService.getAllTestGuides("", "", 0, 10);

        assertTrue(result.iterator().hasNext());
    }

    @Test
    void getAllTestGuides_shouldThrowNotFound_whenGuidesListIsEmpty() {
        when(testGuidesRepository.getAllTestsGuidesFiltered(any(), any(), anyInt(), anyInt())).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> manageGuidesService.getAllTestGuides("", "", 0, 10));
    }

    // ==================== deleteTestGuide ====================

    @Test
    void deleteTestGuide_shouldDeleteGuideAndFile_whenGuideExists() {
        when(testGuide.getTestGuideId()).thenReturn("guia_matematicas");
        when(testGuidesRepository.getTestGuide("guia_matematicas")).thenReturn(Optional.of(testGuide));
        when(filesRepository.deleteFile("guia_matematicas")).thenReturn(true);

        boolean result = manageGuidesService.deleteTestGuide("guia_matematicas");

        assertTrue(result);
        InOrder order = inOrder(testGuidesRepository, filesRepository);
        order.verify(testGuidesRepository).deleteById("guia_matematicas");
        order.verify(filesRepository).deleteFile("guia_matematicas");
    }

    @Test
    void deleteTestGuide_shouldThrowNotFound_whenGuideDoesNotExist() {
        when(testGuidesRepository.getTestGuide("guia_matematicas")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> manageGuidesService.deleteTestGuide("guia_matematicas"));

        verify(testGuidesRepository, never()).deleteById(any());
        verify(filesRepository, never()).deleteFile(any());
    }
}
