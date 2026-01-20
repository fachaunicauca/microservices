package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Constants.TestState;
import com.unicauca.sga.testService.Domain.Exceptions.AlreadyExistsException;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.TestMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.TestJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TestRepository implements ITestRepository {

    private final TestJpaRepository testJpaRepository;
    private final TestMapper testMapper;

    @Override
    public Page<Test> getAllTests(Pageable pageable) {
        return testJpaRepository.findByTestIdNot(1, pageable).map(testMapper::toModel);
    }

    @Override
    public Page<Test> getTeacherTests(String teacherEmail, Pageable pageable) {
        return testJpaRepository.findByTeacherEmail(teacherEmail, pageable).map(testMapper::toModel);
    }

    @Override
    public Page<Test> getAllActiveTests(Pageable pageable) {
        return testJpaRepository.findByTestStateAndTestIdNot(TestState.ACTIVE, 1,pageable).map(testMapper::toModel);
    }

    @Override
    public Optional<Test> getTestById(int id) {
        return testJpaRepository.findById(id).map(testMapper::toModel);
    }

    @Override
    public Test save(Test test) {
        try {
            if (test.getTestId() == null) {
                return testMapper.toModel(testJpaRepository.save(testMapper.toInfra(test)));
            }

            // Se debe obtener la referencia y luego actualizar para que no se pierdan las preguntas
            TestEntity testEntity = testJpaRepository.getReferenceById(test.getTestId());

            testMapper.updateInfra(test, testEntity);

            return testMapper.toModel(testJpaRepository.save(testEntity));

        } catch (DataIntegrityViolationException ex) {
            throw new AlreadyExistsException("El título de la evaluación ya está en uso.");
        }
    }

    @Override
    public void deleteById(int id) {
        testJpaRepository.deleteById(id);
    }

    @Override
    public boolean isPresent(int id) {
        return testJpaRepository.existsById(id);
    }
}
