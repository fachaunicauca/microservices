package com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController;

import com.unicauca.sga.testService.Aplication.UseCases.ManageTestService;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.ManageTestDTO;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.Mappers.ManageTestDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
@Tag(name="Controlador Gestion de Evaluaciones",description="Funcionalidades necesarias para la gestion de las evaluaciones.")
public class ManageTestController {
    private final ManageTestService manageTestService;
    private final ManageTestDTOMapper manageTestDTOMapper;

    public ManageTestController(ManageTestService manageTestService, ManageTestDTOMapper manageTestDTOMapper) {
        this.manageTestService = manageTestService;
        this.manageTestDTOMapper = manageTestDTOMapper;
    }

    @Operation(
            summary = "Obtener evaluación sin preguntas",
            description = "Método para obtener la información de una evaluación por id sin obtener las preguntas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evaluación obtenida con éxito."),
                    @ApiResponse(responseCode = "404", description = "No se encontró la evaluación.")
            }
    )
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public ManageTestDTO getTestById(@PathVariable int id){
        return manageTestDTOMapper.toDTO(manageTestService.getTestById(id));
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
    public List<ManageTestDTO> getAllTests(){
        return manageTestService.getAllTests().stream().map(manageTestDTOMapper::toDTO).toList();
    }
}
