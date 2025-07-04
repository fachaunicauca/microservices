package com.unicauca.sga.testService.Aplication.Services;

import com.unicauca.sga.testService.Domain.Model.Test;
import com.unicauca.sga.testService.Domain.Ports.Repositories.ITestRepository;
import com.unicauca.sga.testService.Domain.Ports.Services.ITestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService implements ITestService {

    private final ITestRepository testRepository;

    @Override
    public List<Test> getAllTests(){
        return testRepository.findAll();
    }

    @Override
    public Test getTestById(long id){
        return testRepository.findById(id);
    }

    @Override
    public Test saveTest(Test test){
        return testRepository.save(test);
    }

    @Override
    public void deleteTest(Test test){
        testRepository.delete(test);
    }

    @Override
    public void deleteTestById(long id){
        testRepository.deleteById(id);
    }

    @Override
    public Test updateTest(Test test){
        return testRepository.save(test);
    }

    @Override
    public boolean isPresent(long id) {
        return testRepository.isPresent(id);
    }

    @Override
    public List<Test> getTestBySemesterAndStudentCode(LocalDate startDate, LocalDate endDate, Long studentId) {
        return testRepository.findBySemesterAndStudentCode(startDate, endDate, studentId);
    }
}
