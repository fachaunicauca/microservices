package com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories.Adapters;

import com.unicauca.sga.courseService.Domain.Exceptions.AlreadyExistsException;
import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentRepository;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Mappers.StudentMapper;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Repositories.StudentJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentRepository implements IStudentRepository {

    private final StudentJPARepository studentJPARepository;
    private final StudentMapper studentMapper;

    @Override
    public Optional<Student> getStudentByEmail(String email) {
        return studentJPARepository.findByStudentEmail(email).map(studentMapper::toModel);
    }

    @Override
    public Page<Student> getAllStudentsPaged(int page, int size) {
        return studentJPARepository.findAll(PageRequest.of(page, size)).map(studentMapper::toModel);
    }

    @Override
    public Student saveStudent(Student student) {
        try{
            return studentMapper.toModel(studentJPARepository.save(studentMapper.toInfra(student)));
        }catch(DataIntegrityViolationException e){
            throw new AlreadyExistsException("El correo ya pertenece a otro estudiante");
        }
    }

    @Override
    public void deleteById(long studentId) {
        studentJPARepository.deleteById(studentId);
    }

    @Override
    public boolean isPresent(long studentId) {
        return studentJPARepository.existsById(studentId);
    }
}
