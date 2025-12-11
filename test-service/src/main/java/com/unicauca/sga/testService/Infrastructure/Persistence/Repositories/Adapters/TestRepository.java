package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.TestMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.TestJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TestRepository implements ITestRepository {

    private final TestJpaRepository testJpaRepository;
    private final TestMapper testMapper;

    private Test toModel(TestTable test) {
        if (test == null) return null;
        return testMapper.toModel(test, new CycleAvoidingMappingContext());
    }

    private TestTable toInfra(Test test) {
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
        return toModel(testJpaRepository.save(toInfra(test)));
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
