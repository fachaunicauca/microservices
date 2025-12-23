package com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController;

import com.unicauca.sga.testService.Aplication.UseCases.ManageQuestionsService;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.DTOs.Response.QuestionDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.Mappers.QuestionDTORequestMapper;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.Mappers.QuestionDTOResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
@Tag(name = "Controlador Gestion de Preguntas", description = "Funcionalidades necesarias para la gestion de preguntas.")
public class ManageQuestionsController {

    private final ManageQuestionsService manageQuestionsService;
    private final QuestionDTOResponseMapper questionDTOResponseMapper;
    private final QuestionDTORequestMapper  questionDTORequestMapper;

    @Operation(
            summary = "Obtener preguntas evaluación por paginas",
            description = "Método para obtener las preguntas de una pagina de una evaluación con su información completa.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Preguntas obtenidas con éxito."),
                    @ApiResponse(responseCode = "204", description = "La evaluación no tiene preguntas almacenadas.")
            }
    )
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public Page<QuestionDTOResponse> getTestQuestionsPaged(@RequestParam("testId") int testId, Pageable pageable){
        return manageQuestionsService.getTestQuestionsPaged(testId, pageable).map(questionDTOResponseMapper::toDTO);
    }
}
