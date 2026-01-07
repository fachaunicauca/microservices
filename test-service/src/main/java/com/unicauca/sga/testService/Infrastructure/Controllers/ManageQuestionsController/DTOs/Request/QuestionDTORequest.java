package com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.DTOs.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionDTORequest {

    private Long questionId;

    @NotBlank(message = "Debe especificar el texto de la pregunta")
    @Size(max = 5000, message = "El texto de la pregunta puede tener como máximo 5000 caracteres")
    private String questionText;

    @Size(max = 1000, message = "El titulo de la pregunta puede tener como máximo 1000 caracteres")
    private String questionTitle;

    @Size(max = 1048576, message = "La imagen no puede pesar mas de 1 MB")
    private byte[] questionImage;

    @NotBlank(message = "Debe especificar el tipo de la pregunta")
    private String questionType;

    @NotBlank(message = "Debe especificar la estructura de la pregunta")
    private String questionStructure;

    @NotNull(message = "Debe especificar la evaluación a la que pertenece la pregunta")
    private Integer testId;
}
