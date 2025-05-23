package com.unicauca.sga.testService.Infrastructure.Controllers;

import com.unicauca.sga.testService.Aplication.UseCases.ManageGuidesService;
import com.unicauca.sga.testService.Domain.Model.DTOs.TestGuideDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.TestGuideListDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.TestGuideRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guides")
@Tag(name="Controlador Gestion de Guias",description="Funcionalidades necesarias para la gestion de las guias de capacitacion.")
public class ManageGuidesController {
    private final ManageGuidesService manageGuidesService;

    public ManageGuidesController(ManageGuidesService manageGuidesService) {
        this.manageGuidesService = manageGuidesService;
    }

    @Operation(summary = "Obtener guias de capacitacion.",
                description = "Obtiene todas las guias de capacitacion registradas en el sistema.",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Guias obtenidas con exito."),
                    @ApiResponse(responseCode = "404", description = "No se encontro ninguna guia.")
                })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT','ROLE_TEACHER','ROLE_LABORATORY_WORKER')")
    public TestGuideListDTO getGuides() {
        return manageGuidesService.getAllTestGuides();
    }

    @Operation(summary = "Subir guias",
            description = "Permite que docentes y administrador",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La guia se guardo exitosamente."),
                    @ApiResponse(responseCode = "500", description = "Ocurrio un error interno al subir el archivo a cloudinary.")
            })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public TestGuideDTO saveGuide(@Valid @ModelAttribute TestGuideRequestDTO testGuideRequestDTO) {
        return manageGuidesService.saveTestGuide(testGuideRequestDTO);
    }
}
