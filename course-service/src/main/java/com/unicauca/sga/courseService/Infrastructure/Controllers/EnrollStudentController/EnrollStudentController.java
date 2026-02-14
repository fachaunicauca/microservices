package com.unicauca.sga.courseService.Infrastructure.Controllers.EnrollStudentController;

import com.unicauca.sga.courseService.Application.UseCases.EnrollStudentService;
import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Infrastructure.Controllers.EnrollStudentController.DTOs.Response.CourseStudentDTOResponse;
import com.unicauca.sga.courseService.Infrastructure.Controllers.EnrollStudentController.DTOs.Response.StudentEnrollmentDTOResponse;
import com.unicauca.sga.courseService.Infrastructure.Controllers.EnrollStudentController.Mappers.CourseStudentEnrollmentDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courses/enrollment")
@Tag(name="Controlador Matricula de Estudiantes", description = "Funcionalidades necesarias para la matricula de estudiantes en los cursos")
public class EnrollStudentController {

    private final EnrollStudentService enrollStudentService;
    private final CourseStudentEnrollmentDTOMapper courseStudentEnrollmentDTOMapper;

    @Operation(
            summary = "Obtener estudiantes de un curso por id",
            description = "Método para obtener los estudiantes matriculados en un curso según el id (Utilizado por el microservicio de evaluaciones)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estudiantes obtenidos con éxito"),
                    @ApiResponse(responseCode = "404", description = "El curso no existe o no tiene estudiantes registrados")
            }
    )
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public Page<CourseStudentDTOResponse> getCourseStudents(@RequestParam("courseId") int courseId, Pageable pageable) {
        return ((Page<Student>) enrollStudentService.getCourseStudents(courseId,
                                                                        pageable.getPageNumber(),
                                                                        pageable.getPageSize())
        ).map(courseStudentEnrollmentDTOMapper::studentToDTO);
    }

    @Operation(
            summary = "Matricular estudiante en curso",
            description = "Método para matricular un estudiante mediante su correo a un curso por el id",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Estudiante matriculado con éxito"),
                    @ApiResponse(responseCode = "404", description = "No se encontró el estudiante o el curso"),
                    @ApiResponse(responseCode = "409", description = "El estudiante ya esta matriculado en el curso")
            }
    )
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public StudentEnrollmentDTOResponse enrollStudentInCourse(@RequestParam("studentEmail") String studentEmail,
                                                              @RequestParam("courseId") int courseId){
        return courseStudentEnrollmentDTOMapper.enrollmentToDTO(enrollStudentService.enrollStudent(studentEmail,courseId));
    }

    @Operation(
            summary = "Eliminar matricula estudiante de curso",
            description = "Método para quitar el registro de un estudiante en un curso mediante los ids",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Matricula eliminada con exito"),
                    @ApiResponse(responseCode = "404", description = "El estudiante no estaba matriculado en el curso")
            }
    )
    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public void unenrollStudent(@RequestParam("studentId") long studentId,
                                @RequestParam("courseId") int courseId){
        enrollStudentService.unenrollStudent(studentId, courseId);
    }

    @Operation(
            summary = "Verificar estudiante en curso",
            description = "Método para verificar si un estudiante pertenece a un curso. (Para el microservicio de evaluaciones)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La verificación fue exitosa")
            }
    )
    @PostMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public boolean studentInCourse(@RequestParam("studentEmail") String studentEmail,
                                   @RequestParam("courseId") int courseId){
        return enrollStudentService.isStudentInCourse(studentEmail,courseId);
    }

}
