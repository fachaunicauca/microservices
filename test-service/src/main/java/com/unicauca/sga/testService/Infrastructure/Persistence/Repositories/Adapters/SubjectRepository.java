package com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.testService.Domain.Model.Subject;
import com.unicauca.sga.testService.Domain.Repositories.ISubjectRepository;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.SubjectMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Repositories.SubjectJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubjectRepository implements ISubjectRepository {
    private final SubjectJpaRepository subjectJpaRepository;
    private final SubjectMapper subjectMapper;

    public SubjectRepository (SubjectJpaRepository subjectJpaRepository, SubjectMapper subjectMapper){
        this.subjectJpaRepository=subjectJpaRepository;
        this.subjectMapper=subjectMapper;
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectJpaRepository.findAll().stream().map(subjectMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public Subject getSubjectById(String id) {
        return subjectMapper.toModel(subjectJpaRepository.findById(id).get());
    }

    @Override
    public void save(Subject subject) {
        subjectJpaRepository.save(subjectMapper.toInfra(subject));
    }

    @Override
    public void delete(Subject subject) {
        subjectJpaRepository.delete(subjectMapper.toInfra(subject));
    }

    @Override
    public void deleteById(String id) {
        subjectJpaRepository.deleteById(id);
    }

    @Override
    public boolean isPresent(String id) {
        return subjectJpaRepository.existsById(id);
    }
}
