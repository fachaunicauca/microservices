package com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController;

import com.unicauca.sga.testService.Aplication.UseCases.ManageQuestionsService;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.DTOs.Request.QuestionDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.DTOs.Response.QuestionDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.Mappers.QuestionDTORequestMapper;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.Mappers.QuestionDTOResponseMapper;
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

    @Operation(
            summary = "Crear o editar pregunta",
            description = "Metodo para agregar o editar una pregunta a una evaluacion",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pregunta creada con exito."),
                    @ApiResponse(responseCode = "400", description = "La estructura de la pregunta no corresponde con su tipo."),
                    @ApiResponse(responseCode = "404", description = "No se encontro el algoritmo de validacion y calificacion de la pregunta.")
            }
    )
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public QuestionDTOResponse saveTestQuestion(@RequestBody @Valid QuestionDTORequest questionDTORequest){
        return questionDTOResponseMapper.toDTO(manageQuestionsService.saveQuestion(questionDTORequestMapper.toModel(questionDTORequest)));
    }

    @Operation(
            summary = "Eliminar pregunta",
            description = "Metodo para eliminar una pregunta de una evaluacion",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Preguntada eliminada con exito."),
                    @ApiResponse(responseCode = "404", description = "No se encontro la pregunta que se quiere eliminar.")
            }
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_TEACHER')")
    public void deleteTestQuestion(@PathVariable long id){
        manageQuestionsService.deleteQuestionById(id);
    }
}
