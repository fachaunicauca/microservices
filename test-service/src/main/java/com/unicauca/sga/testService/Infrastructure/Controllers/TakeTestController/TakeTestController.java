package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController;

import com.unicauca.sga.testService.Aplication.UseCases.TakeTestService;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.TestInfoDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.Mappers.TestInfoDTOResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/takeTest")
@Tag(name = "Controlador Presentar Evaluaciones", description = "Funcionalidades necesarias para que los estudiantes presenten evaluaciones.")
public class TakeTestController {

    private final TakeTestService takeTestService;
    private final TestInfoDTOResponseMapper testInfoDTOResponseMapper;

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
}
