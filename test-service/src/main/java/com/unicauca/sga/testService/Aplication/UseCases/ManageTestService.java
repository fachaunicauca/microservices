package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Domain.Exceptions.AlreadyExistsException;
import com.unicauca.sga.testService.Domain.Exceptions.ForbiddenOperationException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageTestService {
    private final ITestRepository testRepository;

    @Transactional(readOnly = true)
    public List<Test> getAllTests() {
        List<Test> testList = testRepository.getAllTests();

        if (testList.isEmpty()){
            throw new NotFoundException("No hay evaluaciones almacenadas");
        }

        return testList;
    }
    
    @Transactional(readOnly = true)
    public Test getTestById(int id) {
        boolean isPresent = testRepository.isPresent(id);

        if (!isPresent){
            throw new NotFoundException("No se encontró la evaluación con id: " + id);
        }

        return testRepository.getTestById(id);
    }

    @Transactional
    public Test saveTest(Test test) {
        try {
            return testRepository.save(test);
        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyExistsException(
                    "El titulo de la evaluación ya esta en uso."
            );
        }
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
