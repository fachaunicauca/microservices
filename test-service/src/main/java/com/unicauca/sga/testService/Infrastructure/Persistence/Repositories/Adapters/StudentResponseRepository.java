package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Models.StudentResponse;
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

    private StudentResponse toModel(StudentResponseEntity studentResponse){
        if(studentResponse==null) return null;
        return studentResponseMapper.toModel(studentResponse, new CycleAvoidingMappingContext());
    }

    private StudentResponseEntity toInfra(StudentResponse studentResponse){
        if(studentResponse==null) return null;
        return studentResponseMapper.toInfra(studentResponse, new CycleAvoidingMappingContext());
    }

    @Override
    public void save(StudentResponse studentResponse) {
        studentResponseJpaRepository.save(toInfra(studentResponse));
    }

    @Override
    public boolean isPresent(long id) {
        return studentResponseJpaRepository.existsById(id);
    }

    @Override
    public List<StudentResponse> getAllQuestionResponses(long questionId) {
        return studentResponseJpaRepository.findByQuestion_QuestionId(questionId).stream().map(this::toModel).toList();
    }

    @Override
    public List<StudentResponse> getAllTestAttemptResponses(long testAttempt) {
        return studentResponseJpaRepository.findByTestAttempt_TestAttemptId(testAttempt).stream().map(this::toModel).toList();
    }
}
