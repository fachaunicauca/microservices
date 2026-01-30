package com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController;

import com.unicauca.sga.testService.Aplication.UseCases.ManageGuidesService;
import com.unicauca.sga.testService.Domain.Models.TestGuide;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Response.TestGuideDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Request.TestGuideDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.Mappers.TestGuideDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/guides")
@Tag(name="Controlador Gestion de Guias",description="Funcionalidades necesarias para la gestion de las guias de capacitación.")
public class ManageGuidesController {

    private final ManageGuidesService manageGuidesService;
    private final TestGuideDTOMapper testGuideDTOMapper;


    @Operation(summary = "Obtener guias de capacitación.",
                description = "Obtiene todas las guias de capacitación registradas en el sistema.",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Guias obtenidas con éxito."),
                    @ApiResponse(responseCode = "404", description = "No se encontró ninguna guia.")
                })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT','ROLE_TEACHER','ROLE_LABORATORY_WORKER')")
    public List<TestGuide> getGuides() {
        return manageGuidesService.getAllTestGuides();
    }

    @Operation(summary = "Subir guias",
            description = "Permite que docentes y administrador suban guias al servicio de cloudinary.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La guia se guardo exitosamente."),
                    @ApiResponse(responseCode = "500", description = "Ocurrió un error interno al subir el archivo a cloudinary.")
            })
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public TestGuideDTOResponse saveGuide(@Valid @ModelAttribute TestGuideDTORequest testGuideDTORequest) {
        TestGuide testGuide = testGuideDTOMapper.toModel(testGuideDTORequest);

        try{
            testGuide.setTestGuideArchive(testGuideDTORequest.getTestGuideArchive().getBytes());
        }catch(IOException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al leer el archivo de imagen");
        }

        return testGuideDTOMapper.toDTO(manageGuidesService.saveTestGuide(testGuide));
    }

    @Operation(summary = "Eliminar guias",
            description = "Permite que docentes y administrador eliminen guias del servicio de cloudinary.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "La guia se elimino correctamente."),
                    @ApiResponse(responseCode = "404", description = "No se encontró la guia en el servicio de cloudinary.")
            })
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public boolean deleteGuide(@RequestParam("test_guide_id") String test_guide_id) {
        return manageGuidesService.deleteTestGuide(test_guide_id);
    }
}
