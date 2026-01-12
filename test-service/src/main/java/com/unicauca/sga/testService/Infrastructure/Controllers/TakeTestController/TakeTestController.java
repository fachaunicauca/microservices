package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController;

import com.unicauca.sga.testService.Aplication.UseCases.TakeTestService;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Request.StudentTestAttemptDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.StudentTestAttemptDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.TakeTestDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.TestInfoDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.Mappers.StudentTestAttemptDTOMapper;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.Mappers.TakeTestDTOResponseMapper;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.Mappers.TestInfoDTOResponseMapper;
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
@RequestMapping("/takeTest")
@Tag(name = "Controlador Presentar Evaluaciones", description = "Funcionalidades necesarias para que los estudiantes presenten evaluaciones.")
public class TakeTestController {

    private final TakeTestService takeTestService;
    private final TestInfoDTOResponseMapper testInfoDTOResponseMapper;
    private final TakeTestDTOResponseMapper takeTestDTOResponseMapper;
    private final StudentTestAttemptDTOMapper studentTestAttemptDTOMapper;

    @Operation(
            summary = "Obtener evaluaciones especificas activas",
            description = "Método para listar la informacion basica de todas las evaluaciones especificas que se encuentren activas",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evaluaciones obtenidas con exito"),
                    @ApiResponse(responseCode = "404", description = "No se encontraron evaluaciones activas")
            }
    )
    @GetMapping("/tests")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public Page<TestInfoDTOResponse> getActiveTests(Pageable pageable) {
        return takeTestService.getAllActiveTests(pageable).map(testInfoDTOResponseMapper::toDTO);
    }


    @Operation(
            summary = "Obtener evaluacion general",
            description = "Metodo para obtener la informacion basica de la evaluacion general",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evaluacion general obtenida con exito"),
                    @ApiResponse(responseCode = "404", description = "No se encontro la evaluacion general"),
                    @ApiResponse(responseCode = "409", description = "La evaluacion general esta inactiva")
            }
    )
    @GetMapping("/tests/general")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public TestInfoDTOResponse getGeneralTest() {
        return testInfoDTOResponseMapper.toDTO(takeTestService.getGeneralTest());
    }

    @Operation(
            summary = "Empezar intento de evaluacion",
            description = "Metodo para obtener la lista de preguntas de una evaluación e iniciar un intento",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Evaluacion y preguntas obtenidas con exito."),
                    @ApiResponse(responseCode = "403", description = "El estudiante no tiene permitido presentar la evaluacion"),
                    @ApiResponse(responseCode = "404", description = "No se encontró la evaluacion que se quiere presentar."),
                    @ApiResponse(responseCode = "409", description = "La evaluacion se encuentra inactiva.")
            }
    )
    @GetMapping("/{testId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public TakeTestDTOResponse startTestAttempt(@PathVariable int testId, @RequestParam("studentEmail") String studentEmail){
        return takeTestDTOResponseMapper.toDTO(takeTestService.startTestAttempt(studentEmail, testId));
    }

    @Operation(
            summary = "Calificar y guardar intento",
            description = "Metodo para calificar y guardar un intento de una evaluacion de un estudiante",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Intento calificado y almacenado con exito"),
                    @ApiResponse(responseCode = "400", description = "Uno de los campos obligatorios del DTO del intento no se recibio"),
                    @ApiResponse(responseCode = "403", description = "No se inicio un intento antes de presentar la evaluacion"),
                    @ApiResponse(responseCode = "404", description = "La evaluacion, una de las preguntas o la estrategia de calficacion de una pregunta no se encontro")
            }
    )
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_STUDENT')")
    public StudentTestAttemptDTOResponse scoreAndSaveStudentAttempt(@RequestBody @Valid StudentTestAttemptDTORequest studentTestAttemptDTORequest){
        return studentTestAttemptDTOMapper.toDTO(
                takeTestService.saveStudentTestAttempt(studentTestAttemptDTOMapper.toModel(studentTestAttemptDTORequest))
        );
    }
}
