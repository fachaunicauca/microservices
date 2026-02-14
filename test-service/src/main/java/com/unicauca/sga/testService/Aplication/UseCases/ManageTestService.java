package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Domain.Services.ICourseService;
import com.unicauca.sga.testService.Domain.Exceptions.InsufficientQuestionsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Exceptions.ProtectedTestException;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManageTestService {
    private final ITestRepository testRepository;
    private final IQuestionRepository questionRepository;
    private final ICourseService courseService;

    @Transactional(readOnly = true)
    public Iterable<Test> getAllTests(int page, int size) {
        Iterable<Test> testList = testRepository.getAllTests(page, size);

        if (!testList.iterator().hasNext()) {
            throw new NotFoundException("No hay evaluaciones especificas almacenadas");
        }

        return testList;
    }

    @Transactional(readOnly = true)
    public Iterable<Test> getAllTeacherTests(String teacherEmail, int page, int size) {
        Iterable<Test> testList = testRepository.getTeacherTests(teacherEmail, page, size);

        if (!testList.iterator().hasNext()) {
            throw new NotFoundException("No ha creado ninguna evaluación.");
        }

        return testList;
    }
    
    @Transactional(readOnly = true)
    public Test getTestById(int id) {
        return testRepository.getTestById(id).orElseThrow(() ->
                new NotFoundException("No se encontró la evaluación con id: "+ id)
        );
    }

    @Transactional
    public Test saveTest(Test test) {
        Integer testId = test.getTestId();

        // Si se esta editando un test
        if(testId != null) {
            // Revisar que exista el test que se quiere editar
            if(!testRepository.isPresent(testId)) {
                throw new NotFoundException("No se encontró la evaluación que se quiere editar (Id:"+testId+").");
            }
        }

        // Al activar un test, revisar que tenga suficientes preguntas
        if(test.isActive()){
            long totalQuestions = 0;

            if(testId != null) {
                totalQuestions = questionRepository.getTestTotalQuestions(testId);
            }

            if(!test.hasEnoughQuestions(totalQuestions) ){
                throw new InsufficientQuestionsException("La evaluación no tiene la suficiente cantidad"+
                        " de preguntas para estar activa. (Total actual: "+totalQuestions+")");
            }
        }

        // Verificar que el curso exista
        // Si el courseId es 0, no verificar si existe
        Integer courseId = test.getCourseId();
        if(courseId != 0 && !courseService.courseExistsById(courseId)) {
            throw new NotFoundException("No se encontró el curso con ID: "+courseId);
        }

        return testRepository.save(test);
    }

    @Transactional
    public void deleteTestById(int id) {
        if(id == 1){
            throw new ProtectedTestException("No se puede eliminar la evaluación general.");
        }

        boolean isPresent = testRepository.isPresent(id);

        if (!isPresent){
            throw new NotFoundException("No se encontró la evaluación con id: " + id);
        }

        testRepository.deleteById(id);
    }

}
