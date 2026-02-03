package com.unicauca.sga.testService.Infrastructure.Controllers.ManageStudentTestConfigController;

import com.unicauca.sga.testService.Aplication.UseCases.ManageStudentTestConfigsService;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageStudentTestConfigController.DTOs.Response.StudentTestConfigDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageStudentTestConfigController.Mappers.StudentTestConfigDTOMapper;
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
@RequestMapping("/testConfigs")
@Tag(name = "Controlador Gestion de Configuraciones de Evaluación",
        description = "Funcionalidades necesarias para la gestion de las configuraciones de evaluaciones de los estudiantes")
public class ManageStudentTestConfigController {
    private final ManageStudentTestConfigsService manageStudentTestConfigsService;
    private final StudentTestConfigDTOMapper studentTestConfigDTOMapper;

    @Operation(summary = "Obtener configuraciones de evaluacion prioritarias",
                description = "Obtiene las configuraciones de los estudiantes en una evaluacion que han solicitado restablecer sus intentos",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Configuraciones obtenidas con exito"),
                    @ApiResponse(responseCode = "404", description = "La evaluacion ya no existe o no hay ninguna configuracion prioritaria")
                }
    )
    @GetMapping("/{testId}/priority")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public Page<StudentTestConfigDTOResponse> getAllPriorityStudentTestConfigsPaged (@PathVariable int testId, Pageable pageable){
        return ((Page<StudentTestConfig>) manageStudentTestConfigsService.getPriorityStudentTestConfigsPaged(testId,
                                                                                                            pageable.getPageNumber(),
                                                                                                            pageable.getPageSize())
        ).map(studentTestConfigDTOMapper::toDTO);
    }

    @Operation(summary = "Obtener configuraciones de evaluacion no prioritarias",
                description = "Obtiene las configuraciones de los estudiantes en una evaluacion pero que no han solicitado restablecer sus intentos",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Configuraciones obtenidas con exito"),
                    @ApiResponse(responseCode = "404", description = "La evaluacion ya no existe o no hay ninguna configuracion")
                }
    )
    @GetMapping("/{testId}")
    public Page<StudentTestConfigDTOResponse> getAllNonPriorityStudentTestConfigsPaged (@PathVariable int testId, Pageable pageable){
        return ((Page<StudentTestConfig>) manageStudentTestConfigsService.getStudentTestConfigsPaged(testId,
                                                                                                    pageable.getPageNumber(),
                                                                                                    pageable.getPageSize())
        ).map(studentTestConfigDTOMapper::toDTO);
    }

}
