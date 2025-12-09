package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Model.Test;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.TestMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.TestJpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TestRepository implements ITestRepository {
    private final TestJpaRepository testJpaRepository;
    private final TestMapper testMapper;

    public TestRepository(TestJpaRepository testJpaRepository, TestMapper testMapper){
        this.testJpaRepository=testJpaRepository;
        this.testMapper=testMapper;
    }

    @Override
    public List<Test> getAllTests() {
        return testJpaRepository.findAll().stream().map(testMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Test getTestById(long id) {
        return testMapper.toModel(testJpaRepository.findById(id).get());
    }

    @Override
    public Test save(Test test) {
        return testMapper.toModel(testJpaRepository.save(testMapper.toInfra(test)));
    }

    @Override
    public void delete(Test test) {
        testJpaRepository.delete(testMapper.toInfra(test));
    }

    @Override
    public void deleteById(long id) {
        testJpaRepository.deleteById(id);
    }

    @Override
    public boolean isPresent(long id) {
        return testJpaRepository.existsById(id);
    }

    @Override
    public List<Test> getTestBySemesterAndStudentCode(LocalDate startDate, LocalDate endDate, Long studentId) {
        return testJpaRepository.findByTestDateBetweenAndStudentId(startDate, endDate, studentId).stream().map(testMapper::toModel).collect(Collectors.toList());
    }
}
