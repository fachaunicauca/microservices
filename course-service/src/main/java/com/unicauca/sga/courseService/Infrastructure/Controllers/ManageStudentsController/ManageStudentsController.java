package com.unicauca.sga.courseService.Infrastructure.Controllers.ManageStudentsController;

import com.unicauca.sga.courseService.Application.UseCases.ManageStudentsService;
import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageStudentsController.DTOs.Request.StudentDTORequest;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageStudentsController.DTOs.Response.StudentDTOResponse;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageStudentsController.Mappers.StudentDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
@Tag(name="Controlador Gestion de Estudiantes", description = "Funcionalidades necesarias para la gestion de estudiantes")
public class ManageStudentsController {
    private final ManageStudentsService manageStudentsService;
    private final StudentDTOMapper studentDTOMapper;

    @Operation(
            summary = "Obtener todos los estudiantes registrados",
            description = "Metodo para obtener todos los estudiantes que esten registrados en la base de datos de cursos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estudiantes obtenidos con exito"),
                    @ApiResponse(responseCode = "404", description = "No se encontraron estudiantes registrados")
            }
    )
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public Page<StudentDTOResponse> getAllStudents(Pageable pageable) {
        return ((Page<Student>) manageStudentsService.getAllStudentsPaged(
                pageable.getPageNumber(),
                pageable.getPageSize())
        ).map(studentDTOMapper::toDTO);
    }

    @Operation(
            summary = "Crear o actualizar informacion estudiante",
            description = "Metodo para crear o editar la informacion de un estudiante almacenada en la base de datos de cursos",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Estudiante creado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "No se encontró el estudiante que se quiere editar"),
                    @ApiResponse(responseCode = "409", description = "El correo del estudiante ya esta en uso")
            }
    )
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public StudentDTOResponse saveStudent(@RequestBody @Valid StudentDTORequest studentDTORequest) {
        return studentDTOMapper.toDTO(manageStudentsService.saveStudent(studentDTOMapper.toModel(studentDTORequest)));
    }

    @Operation(
            summary = "Eliminar estudiante por id",
            description = "Metodo para eliminar por id el registro de un estudiante en la base de datos de cursos",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estudiante eliminado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "No se encontró el estudiante que se quiere eliminar")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public void deleteStudentById(@PathVariable long id) {
        manageStudentsService.deleteStudentById(id);
    }
}
