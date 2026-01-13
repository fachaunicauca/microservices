package com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController;

import com.unicauca.sga.testService.Aplication.UseCases.ManageTestService;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.Request.TestDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.Response.TestDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.Mappers.TestDTOMapper;
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
@RequestMapping("/tests")
@Tag(name="Controlador Gestion de Evaluaciones",description="Funcionalidades necesarias para la gestion de las evaluaciones.")
public class ManageTestController {
    private final ManageTestService manageTestService;
    private final TestDTOMapper testDTOMapper;

    @Operation(
            summary = "Obtener evaluación sin preguntas",
            description = "Método para obtener la información de una evaluación por id sin obtener las preguntas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evaluación obtenida con éxito."),
                    @ApiResponse(responseCode = "404", description = "No se encontró la evaluación.")
            }
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public TestDTOResponse getTestById(@PathVariable int id){
        return testDTOMapper.toDTO(manageTestService.getTestById(id));
    }

    @Operation(
            summary = "Obtener todas las evaluaciones docente sin preguntas",
            description = "Método para obtener la información de todas las evaluaciones creadas por un docente sin obtener las preguntas."+
                                "Si el token tiene el rol de admin se le devuelven todas las evaluaciones almacenadas.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evaluaciones obtenidas con éxito."),
                    @ApiResponse(responseCode = "404", description = "No hay evaluaciones almacenadas.")
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public Page<TestDTOResponse> getAllTests(Pageable pageable){
        return this.getTests(pageable);
    }

    @Operation(
            summary = "Crear o actualizar evaluación específica",
            description = "Método para crear o actualizar evaluaciones específicas sin las preguntas.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Evaluación creada exitosamente."),
                    @ApiResponse(responseCode = "400", description = "Se ha violado alguna(s) de las validaciones."),
                    @ApiResponse(responseCode = "404", description = "No se ha encontrado la evaluación que se quiere editar."),
                    @ApiResponse(responseCode = "409", description = "El titulo de la evaluación ya esta en uso.")
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public TestDTOResponse saveTest(@RequestBody @Valid TestDTORequest testDTORequest){
        return testDTOMapper.toDTO(manageTestService.saveTest(testDTOMapper.toModel(testDTORequest)));
    }

    @Operation(
            summary = "Eliminar una evaluación",
            description = "Método para eliminar una evaluación mediante su id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La evaluación se ha eliminado exitosamente."),
                    @ApiResponse(responseCode = "404", description = "No se encontró la evaluación que se quiere eliminar."),
                    @ApiResponse(responseCode = "403", description = "No se puede eliminar la evaluación general.")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public void deleteTestById(@PathVariable int id){
        manageTestService.deleteTestById(id);
    }

    private Page<TestDTOResponse> getTests(Pageable pageable){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return manageTestService.getAllTests(pageable).map(testDTOMapper::toDTO);
        }

        Jwt jwt = (Jwt) auth.getPrincipal();
        String email = jwt.getClaimAsString("email");

        return manageTestService.getAllTeacherTests(email, pageable).map(testDTOMapper::toDTO);
    }
}
