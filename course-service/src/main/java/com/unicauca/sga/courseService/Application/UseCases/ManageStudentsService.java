package com.unicauca.sga.courseService.Application.UseCases;

import com.unicauca.sga.courseService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentEnrollmentRepository;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ManageStudentsService {

    private final IStudentRepository studentRepository;
    private final IStudentEnrollmentRepository studentEnrollmentRepository;

    @Transactional(readOnly = true)
    public Iterable<Student> getAllStudentsPaged(int page, int size) {
        Iterable<Student> paged = studentRepository.getAllStudentsPaged(page, size);

        if(!paged.iterator().hasNext()) {
            throw new NotFoundException("No hay estudiantes registrados");
        }

        return paged;
    }

    @Transactional
    public Student saveStudent(Student student) {
        Long id = student.getStudentId();
        // Si se esta editando
        if(id != null) {
            // Verificar que exista el estudiante que se quiere editar
            if(!studentRepository.isPresent(id)) {
                throw new NotFoundException("No se encontró el estudiante que se quiere editar (Id: "+id+")");
            }
        }

        return studentRepository.saveStudent(student);
    }

    @Transactional
    public void deleteStudentById(long id) {
        if(!studentRepository.isPresent(id)) {
            throw new NotFoundException("No se encontró el estudiante que se quiere eliminar (Id: "+id+")");
        }

        // Eliminar las matrículas que tenga el estudiante
        studentEnrollmentRepository.deleteByStudentId(id);

        studentRepository.deleteById(id);
        // ¿Eliminar el historial de intentos del estudiante?
    }

    @Transactional(readOnly = true)
    public List<Student> getStudentsByEmails(List<String> emails) {
        return studentRepository.getAllStudentsByEmails(emails);
    }
}
