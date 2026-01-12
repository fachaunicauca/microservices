package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StudentTestAttemptDTORequest {
    @NotNull(message = "No se ha recibido el id de la evaluación")
    private Integer testId;

    @NotBlank(message = "No se ha recibido el correo del estudiante")
    private String studentEmail;

    @NotNull(message = "No se ha recibido la cantidad de preguntas que tuvo la evaluación")
    private Integer testAttemptNumberOfQuestions;

    private List<StudentResponseDTORequest> studentResponses;
}
