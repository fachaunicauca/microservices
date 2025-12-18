package com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController;

import com.unicauca.sga.testService.Aplication.UseCases.ManageTestService;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.Request.TestDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.Response.TestDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.Mappers.TestDTORequestMapper;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.Mappers.TestDTOResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tests")
@Tag(name="Controlador Gestion de Evaluaciones",description="Funcionalidades necesarias para la gestion de las evaluaciones.")
public class ManageTestController {
    private final ManageTestService manageTestService;
    private final TestDTOResponseMapper testDTOResponseMapper;
    private final TestDTORequestMapper testDTORequestMapper;

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
        return testDTOResponseMapper.toDTO(manageTestService.getTestById(id));
    }

    @Operation(
            summary = "Obtener todas las evaluaciones sin preguntas",
            description = "Método para obtener la información de todas las evaluaciones almacenadas sin obtener las preguntas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evaluaciones obtenidas con éxito."),
                    @ApiResponse(responseCode = "404", description = "No hay evaluaciones almacenadas.")
            }
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public List<TestDTOResponse> getAllTests(){
        return manageTestService.getAllTests().stream().map(testDTOResponseMapper::toDTO).toList();
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
        System.out.println("Test recibido: " + testDTORequest.toString());
        System.out.println("Test mapeado: " + testDTORequestMapper.toModel(testDTORequest));
        return testDTOResponseMapper.toDTO(manageTestService.saveTest(testDTORequestMapper.toModel(testDTORequest)));
    }

    @Operation(
            summary = "Eliminar una evaluación",
            description = "Metodo para eliminar una evaluacion mediante su id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La evaluacion se ha eliminado exitosamente."),
                    @ApiResponse(responseCode = "404", description = "No se encontro la evaluacion que se quiere eliminar.")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public void deleteTestById(@PathVariable int id){
        manageTestService.deleteTestById(id);
    }
}
