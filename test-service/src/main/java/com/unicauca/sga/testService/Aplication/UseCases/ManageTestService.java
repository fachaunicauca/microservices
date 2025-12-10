package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Repositories.ITestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageTestService {
    private final ITestRepository testRepository;

    @Transactional(readOnly = true)
    public List<Test> getAllTests() {
        return testRepository.getAllTests();
    }
    
    @Transactional(readOnly = true)
    public Test getTestById(int id) {
        return testRepository.getTestById(id);
    }

}
