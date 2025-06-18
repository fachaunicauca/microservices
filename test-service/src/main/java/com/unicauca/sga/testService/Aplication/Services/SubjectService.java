package com.unicauca.sga.testService.Aplication.Services;

import com.unicauca.sga.testService.Domain.Model.Subject;
import com.unicauca.sga.testService.Domain.Ports.Repositories.ISubjectRepository;
import com.unicauca.sga.testService.Domain.Ports.Services.ISubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService implements ISubjectService {

    private final ISubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects(){
        return (List<Subject>) subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(String id){
        return subjectRepository.findById(id);
    }

    @Override
    public void saveSubject(Subject subject){
        subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Subject subject){
        subjectRepository.delete(subject);
    }

    @Override
    public void deleteSubjectById(String id){
        subjectRepository.deleteById(id);
    }

    @Override
    public void updateSubject(Subject subject){
        subjectRepository.save(subject);
    }

    @Override
    public boolean isPresent(String id) {
        return subjectRepository.isPresent(id);
    }
}
