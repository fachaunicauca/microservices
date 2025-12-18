package com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDTORequest {
    private Integer testId;

    @NotNull(message = "La evaluación debe estar asociada a un docente.")
    @NotBlank(message = "El correo del docente no puede estar vacío.")
    private String teacherEmail;

    @NotNull(message = "El titulo no puede ser nulo.")
    @NotBlank(message = "El titulo no puede estar vacío.")
    private String testTitle;

    private String testDescription;

    @NotNull(message = "Debe especificar una duración (0 sin limite de tiempo).")
    @PositiveOrZero(message = "La duración no puede ser negativa.")
    private Integer testDurationMinutes;

    @NotNull(message = "Debe especificar la cantidad de preguntas que deben responder los estudiantes.")
    @Min(value = 4, message = "La cantidad de preguntas que deben responder los estudiantes debe ser superior a 3.")
    private Integer testNumberOfQuestions;

    @NotNull(message = "Debe especificar la cantidad limite de intentos (0 sin limite de intentos).")
    @PositiveOrZero(message = "La cantidad limite de intentos no puede ser negativa.")
    private Integer testAttemptLimit;

    @NotNull(message = "Debe especificar el estado de la evaluación.")
    @PositiveOrZero(message = "El estado de la evaluación no puede ser negativo.")
    private Byte testState;

    @NotNull(message = "Debe especificar si la evaluación se realizara semestralmente.")
    @JsonProperty("isPeriodic")
    private Boolean periodic;
}
