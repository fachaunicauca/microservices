package com.unicauca.sga.courseService.Infrastructure.Controllers.ManageCoursesController;

import com.unicauca.sga.courseService.Application.UseCases.ManageCoursesService;
import com.unicauca.sga.courseService.Domain.Models.Course;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageCoursesController.DTOs.Request.CourseDTORequest;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageCoursesController.DTOs.Response.CourseDTOResponse;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageCoursesController.Mappers.CourseDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
@Tag(name="Controlador Gestion de Cursos", description = "Funcionalidades necesarias para la gestion de cursos")
public class ManageCoursesController {

    private final ManageCoursesService manageCoursesService;
    private final CourseDTOMapper courseDTOMapper;

    @Operation(
            summary = "Obtener todos los cursos docente",
            description = "Metodo para obtener todos los cursos que haya creado un docente." +
                    "Si el token tiene el rol de admin se le devuelven todos los cursos que haya almacenados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cursos obtenidos con éxito"),
                    @ApiResponse(responseCode = "404", description = "No hay cursos almacenados")
            }
    )
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public Page<CourseDTOResponse> getAllCourses(Pageable pageable) {
        return this.getCourses(pageable);
    }

    @Operation(
            summary = "Obtener curso",
            description = "Método para obtener un curso por su id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Curso obtenido con éxito"),
                    @ApiResponse(responseCode = "404", description = "No se encontró el curso")
            }
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public CourseDTOResponse getCourseById(@PathVariable int id) {
        return courseDTOMapper.toDTO(manageCoursesService.getCourseById(id));
    }

    @Operation(
            summary = "Crear o actualizar un curso",
            description = "Método para crear o editar un curso",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Curso creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Se ha violado alguna(s) de las validaciones"),
                    @ApiResponse(responseCode = "404", description = "No se ha encontrado el curso que se quiere editar")
            }
    )
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public CourseDTOResponse saveCourse(@RequestBody @Valid CourseDTORequest courseDTORequest) {
        return courseDTOMapper.toDTO(manageCoursesService.save(courseDTOMapper.toModel(courseDTORequest)));
    }

    @Operation(
            summary = "Eliminar un curso",
            description = "Método para eliminar un curso por su id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Curso eliminado con éxito"),
                    @ApiResponse(responseCode = "404", description = "No se encontró el curso a eliminar")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public void deleteCourseById(@PathVariable int id) {
        manageCoursesService.deleteById(id);
    }

    private Page<CourseDTOResponse> getCourses(Pageable pageable) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return ((Page<Course>) manageCoursesService.getAllCoursesPaged(
                    pageable.getPageNumber(),
                    pageable.getPageSize())
            ).map(courseDTOMapper::toDTO);
        }

        Jwt jwt = (Jwt) auth.getPrincipal();
        String email = jwt.getClaimAsString("email");

        return ((Page<Course>) manageCoursesService.getTeacherCoursesPaged(
                email,
                pageable.getPageNumber(),
                pageable.getPageSize())
        ).map(courseDTOMapper::toDTO);
    }


}
