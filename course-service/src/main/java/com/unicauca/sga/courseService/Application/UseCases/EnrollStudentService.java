package com.unicauca.sga.courseService.Application.UseCases;

import com.unicauca.sga.courseService.Domain.Exceptions.AlreadyEnrolledException;
import com.unicauca.sga.courseService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.courseService.Domain.Models.Course;
import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Domain.Models.StudentEnrollment;
import com.unicauca.sga.courseService.Domain.Repositories.ICourseRepository;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentEnrollmentRepository;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollStudentService {
    private final ICourseRepository courseRepository;
    private final IStudentRepository studentRepository;
    private final IStudentEnrollmentRepository studentEnrollmentRepository;

    @Transactional(readOnly = true)
    public Iterable<Student> getCourseStudents(int courseId, int page, int size) {
        if(!courseRepository.isPresent(courseId)){
            throw new NotFoundException("El curso con id: "+courseId+" no existe");
        }

        Iterable<Student> paged = studentEnrollmentRepository.getCourseStudents(courseId, page, size);

        if(!paged.iterator().hasNext()){
            throw new NotFoundException("No hay estudiantes registrados en este curso");
        }

        return paged;
    }

    @Transactional
    public StudentEnrollment enrollStudent(String studentEmail, int courseId){
        Student student = studentRepository.getStudentByEmail(studentEmail).orElseThrow(()->
                new NotFoundException("No se encontró el estudiante")
        );

        Course course = courseRepository.getCourseById(courseId).orElseThrow(()->
                new NotFoundException("No se encontró el curso")
        );

        if(studentEnrollmentRepository.isStudentInCourse(student.getStudentId(), courseId)){
            throw new AlreadyEnrolledException("El estudiante ya ha sido asignado al curso");
        }

        return studentEnrollmentRepository.saveStudentEnrollment(new StudentEnrollment(student, course));
    }

    @Transactional
    public void unenrollStudent(long studentId, int courseId){
        studentEnrollmentRepository.getStudentEnrollment(studentId, courseId).ifPresent(
                enrollment -> studentEnrollmentRepository.deleteById(enrollment.getStudentEnrollmentId())
        );
    }

    @Transactional
    public boolean isStudentInCourse(long studentId, int courseId){
        return studentEnrollmentRepository.isStudentInCourse(studentId, courseId);
    }
}
