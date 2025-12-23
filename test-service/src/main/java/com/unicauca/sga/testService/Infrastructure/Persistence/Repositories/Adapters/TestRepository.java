package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Exceptions.AlreadyExistsException;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.TestMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.TestJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TestRepository implements ITestRepository {

    private final TestJpaRepository testJpaRepository;
    private final TestMapper testMapper;

    private Test toModel(TestEntity test) {
        if (test == null) return null;
        return testMapper.toModel(test, new CycleAvoidingMappingContext());
    }

    private TestEntity toInfra(Test test) {
        if (test == null) return null;
        return testMapper.toInfra(test, new CycleAvoidingMappingContext());
    }

    @Override
    public List<Test> getAllTests() {
        return testJpaRepository.findAll().stream().map(this::toModel).toList();
    }

    @Override
    public List<Test> getTeacherTests(String teacherEmail) {
        return testJpaRepository.findByTeacherEmail(teacherEmail).stream().map(this::toModel).toList();
    }

    @Override
    public Test getTestById(int id) {
        return toModel(testJpaRepository.findById(id).get());
    }

    @Override
    public Test save(Test test) {
        try {
            if (test.getTestId() == 0) {
                return toModel(testJpaRepository.save(toInfra(test)));
            }

            TestEntity testEntity = testJpaRepository.getReferenceById(test.getTestId());

            testMapper.update(test, testEntity);

            return toModel(testJpaRepository.save(testEntity));

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
