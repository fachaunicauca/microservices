package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Repositories.IStudentTestConfigRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.StudentTestConfigMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.StudentTestConfigJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentTestConfigRepository implements IStudentTestConfigRepository {

    private final StudentTestConfigJpaRepository studentTestConfigJpaRepository;
    private final StudentTestConfigMapper studentTestConfigMapper;

    @Override
    public void save(StudentTestConfig studentTestConfig) {
        studentTestConfigJpaRepository.save(studentTestConfigMapper.toInfra(studentTestConfig));
    }

    @Override
    public Optional<StudentTestConfig> getStudentTestConfig(String studentEmail, int testId) {
        return studentTestConfigJpaRepository.findByStudentEmailAndTest_TestId(studentEmail, testId).map(studentTestConfigMapper::toModel);
    }

}
