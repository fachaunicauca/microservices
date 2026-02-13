package com.unicauca.sga.testService.Infrastructure.Controllers.ManageAttemptRequestsController;

import com.unicauca.sga.testService.Aplication.UseCases.ManageAttemptRequestsService;
import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageAttemptRequestsController.DTOs.Response.AttemptRequestDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageAttemptRequestsController.Mappers.AttemptRequestDTOMapper;
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
@RequestMapping("/tests")
@Tag(name = "Controlador Gestion de Configuraciones de Evaluación",
        description = "Funcionalidades necesarias para la gestion de las configuraciones de evaluaciones de los estudiantes")
public class ManageAttemptRequestsController {
    private final ManageAttemptRequestsService manageAttemptRequestsService;
    private final AttemptRequestDTOMapper attemptRequestDTOMapper;

    @Operation(summary = "Obtener solicitudes de restablecimiento de intentos",
                description = "Obtiene las configuraciones de los estudiantes en una evaluación que han solicitado restablecer sus intentos",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Configuraciones obtenidas con éxito"),
                    @ApiResponse(responseCode = "404", description = "La evaluación ya no existe o ningún estudiante ha solicitado el restablecimiento")
                }
    )
    @GetMapping("/{testId}/requests")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public Page<AttemptRequestDTOResponse> getPendingAttemptRequestPaged (@PathVariable int testId, Pageable pageable){
        return ((Page<StudentTestConfig>) manageAttemptRequestsService.getPendingAttemptRequestPaged(testId,
                                                                                                    pageable.getPageNumber(),
                                                                                                    pageable.getPageSize())
        ).map(attemptRequestDTOMapper::toDTO);
    }

    @Operation(summary = "Solicitar restablecer intentos",
                description = "Método para que los estudiantes puedan solicitar a los docentes restablecer sus intentos en una evaluacion",
                responses = {
                    @ApiResponse(responseCode = "200", description = "Solicitud realizada con exito"),
                    @ApiResponse(responseCode = "404", description = "El estudiante no ha presentado intentos de la evaluacion"),
                    @ApiResponse(responseCode = "409", description = "El estudiante todavía tiene intentos disponibles")
                }
    )
    @PostMapping("/{testId}/requests")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public void requestAttempts(@PathVariable int testId, @RequestParam String studentEmail){
        manageAttemptRequestsService.requestAttempts(studentEmail, testId);
    }

    @Operation(
            summary = "Restablecer intentos",
            description = "Método para restablecer los intentos de un estudiante en una evaluación",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Intentos restablecidos exitosamente"),
                    @ApiResponse(responseCode = "404", description = "No se encontró la configuración del estudiante")
            }
    )
    @PostMapping("/{testId}/requests/reset")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public void resetAttempts(@PathVariable int testId, @RequestParam String studentEmail){
        manageAttemptRequestsService.resetAttempts(studentEmail, testId);
    }
}
