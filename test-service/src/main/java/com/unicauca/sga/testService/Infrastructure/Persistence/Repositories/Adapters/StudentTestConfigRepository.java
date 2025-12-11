package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.StudentTestConfigMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.StudentTestConfigJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentTestConfigRepository implements IStudentTestConfigRepository {

    private final StudentTestConfigJpaRepository studentTestConfigJpaRepository;
    private final StudentTestConfigMapper studentTestConfigMapper;

    private StudentTestConfig toModel(StudentTestConfigTable studentTestConfig) {
        if (studentTestConfig == null) return null;
        return studentTestConfigMapper.toModel(studentTestConfig, new CycleAvoidingMappingContext());
    };

    private StudentTestConfigTable toInfra(StudentTestConfig studentTestConfig) {
        if (studentTestConfig == null) return null;
        return studentTestConfigMapper.toInfra(studentTestConfig, new CycleAvoidingMappingContext());
    }

    @Override
    public void save(StudentTestConfig studentTestConfig) {
        studentTestConfigJpaRepository.save(toInfra(studentTestConfig));
    }

    @Override
    public StudentTestConfig getStudentTestConfig(String studentEmail, int testId) {
        return toModel(studentTestConfigJpaRepository.findByStudentEmailAndTest_TestId(studentEmail, testId));
    }
}
