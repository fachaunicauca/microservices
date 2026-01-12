package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentResponseDTORequest {
    @NotNull(message = "Una de las respuestas no tiene el id de la pregunta")
    private Long questionId;
    @NotNull(message = "No se recibió una de las respuestas")
    private String response;
}
