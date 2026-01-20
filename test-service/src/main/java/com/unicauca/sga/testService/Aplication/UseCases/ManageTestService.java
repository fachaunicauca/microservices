package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Domain.Exceptions.ForbiddenOperationException;
import com.unicauca.sga.testService.Domain.Exceptions.InsufficientQuestionsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManageTestService {
    private final ITestRepository testRepository;
    private final IQuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public Page<Test> getAllTests(Pageable pageable) {
        Page<Test> testList = testRepository.getAllTests(pageable);

        if (testList.isEmpty() || testList.getTotalElements() == 0) {
            throw new NotFoundException("No hay evaluaciones especificas almacenadas");
        }

        return testList;
    }

    @Transactional(readOnly = true)
    public Page<Test> getAllTeacherTests(String teacherEmail, Pageable pageable) {
        Page<Test> testList = testRepository.getTeacherTests(teacherEmail, pageable);

        if (testList.isEmpty() || testList.getTotalElements() == 0) {
            throw new NotFoundException("No ha creado ninguna evaluación.");
        }

        return testList;
    }
    
    @Transactional(readOnly = true)
    public Test getTestById(int id) {
        return testRepository.getTestById(id).orElseThrow(() ->
                new ForbiddenOperationException("Debe iniciar un intento antes de poder guardarlo")
        );
    }

    @Transactional
    public Test saveTest(Test test) {
        // Al editar un test que está activo, revisar que tenga suficientes preguntas
        if(test.getTestId() != null && test.isActive()){
            long totalQuestions = questionRepository.getTestTotalQuestions(test.getTestId());
            if(!test.hasEnoughQuestions(totalQuestions) ){
                throw new InsufficientQuestionsException("La evaluación no tiene la suficiente cantidad"+
                                                            " de preguntas para estar activa. (Total actual: "+totalQuestions+")");
            };
        }
        return testRepository.save(test);
    }

    @Transactional
    public void deleteTestById(int id) {
        if(id == 1){
            throw new ForbiddenOperationException("No se puede eliminar la evaluación general.");
        }

        boolean isPresent = testRepository.isPresent(id);

        if (!isPresent){
            throw new NotFoundException("No se encontró la evaluación con id: " + id);
        }

        testRepository.deleteById(id);
    }

}
