package com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController;

import com.unicauca.sga.testService.Aplication.UseCases.GenerateTestResultsService;
import com.unicauca.sga.testService.Domain.Models.TestResults.StudentTestResult;
import com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.DTOs.StudentsResultsDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.DTOs.TestStatsDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.Mappers.TestResultsDTOMapper;
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
@RequestMapping("/tests/results")
@Tag(name = "Controlador Generador de Resultados de Evaluaciones")
public class GenerateTestResultsController {
    private final GenerateTestResultsService generateTestResultsService;
    private final TestResultsDTOMapper testResultsDTOMapper;

    @Operation(
            summary = "Obtener resultados evaluación",
            description = "Método para obtener los resultados de los estudiantes en una evaluación",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resultados obtenidos exitosamente"),
                    @ApiResponse(responseCode = "404", description = "No se encontró la evaluación o el curso no tiene estudiantes")
            }
    )
    @GetMapping("/{testId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public Page<StudentsResultsDTOResponse> getTestResultsPaged(@PathVariable int testId, Pageable pageable){
        return ((Page<StudentTestResult>) generateTestResultsService.getTestResultsPaged(testId,
                                                                pageable.getPageNumber(),
                                                                pageable.getPageSize())
        ).map(testResultsDTOMapper::studentResultstoDTO);
    }

    @Operation(
            summary = "Obtener estadisticas evaluacion",
            description = "Metodo para obtener las estadisticas de los resultados de los estudiantes en una evaluacion",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Estadisticas obtenidas con exito"),
                    @ApiResponse(responseCode = "404", description = "No se encontro la evaluacion")
            }
    )
    @GetMapping("/{testId}/stats")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public TestStatsDTOResponse getTestStats(@PathVariable int testId){
        return testResultsDTOMapper.testStatsToDTO(generateTestResultsService.getTestStats(testId));
    }
}
