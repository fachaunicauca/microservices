package com.unicauca.sga.courseService.Application.UseCases;

import com.unicauca.sga.courseService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.courseService.Domain.Models.Course;
import com.unicauca.sga.courseService.Domain.Repositories.ICourseRepository;
import com.unicauca.sga.courseService.Domain.Repositories.IStudentEnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ManageCoursesService {

    private final ICourseRepository courseRepository;
    private final IStudentEnrollmentRepository studentEnrollmentRepository;

    @Transactional(readOnly = true)
    public Iterable<Course> getAllCoursesPaged(int page, int size) {
        Iterable<Course> paged = courseRepository.getAllCoursesPaged(page, size);

        if(!paged.iterator().hasNext()) {
            throw new NotFoundException("No hay cursos almacenados");
        }

        return paged;
    }

    @Transactional(readOnly = true)
    public Iterable<Course> getTeacherCoursesPaged(String teacherEmail, int page, int size) {
        Iterable<Course> paged = courseRepository.getTeacherCoursesPaged(teacherEmail, page, size);

        if(!paged.iterator().hasNext()) {
            throw new NotFoundException("No ha creado ningún curso");
        }

        return paged;
    }

    @Transactional
    public Course getCourseById(int id) {
        return courseRepository.getCourseById(id).orElseThrow(() ->
                new NotFoundException("No se encontró el curso con id: "+id)
        );
    }

    @Transactional
    public Course save(Course course) {
        Integer id = course.getCourseId();
        // Si se esta editando un curso
        if(id != null) {
            // Revisar que el curso exista
            if(!courseRepository.isPresent(id)){
                throw new NotFoundException("No se encontró el curso que se quiere editar (Id: "+id+")");
            }
        }

        return courseRepository.saveCourse(course);
    }

    @Transactional
    public void deleteById(int id) {
        if(!courseRepository.isPresent(id)) {
            throw new NotFoundException("No se encontró el curso que se quiere eliminar (Id: "+id+")");
        }

        // Eliminar las matrículas de los estudiantes
        studentEnrollmentRepository.deleteByCourseId(id);

        courseRepository.deleteById(id);
        // ¿Al eliminar un curso deberían eliminarse sus evaluaciones???
    }


}
