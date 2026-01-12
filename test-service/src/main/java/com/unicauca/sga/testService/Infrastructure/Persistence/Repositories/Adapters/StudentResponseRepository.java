package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import com.unicauca.sga.testService.Domain.Repositories.IStudentResponseRepository;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.StudentResponseMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.StudentResponseJpaRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentResponseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StudentResponseRepository implements IStudentResponseRepository {

    private final StudentResponseJpaRepository studentResponseJpaRepository;
    private final StudentResponseMapper studentResponseMapper;

    @Override
    public void save(StudentResponse studentResponse) {
        studentResponseJpaRepository.save(studentResponseMapper.toInfra(studentResponse));
    }

    @Override
    public boolean isPresent(long id) {
        return studentResponseJpaRepository.existsById(id);
    }

    @Override
    public List<StudentResponse> getAllQuestionResponses(long questionId) {
        return studentResponseJpaRepository.findByQuestion_QuestionId(questionId).stream().map(studentResponseMapper::toModel).toList();
    }

    @Override
    public List<StudentResponse> getAllTestAttemptResponses(long testAttempt) {
        return studentResponseJpaRepository.findByTestAttempt_TestAttemptId(testAttempt).stream().map(studentResponseMapper::toModel).toList();
    }
}
