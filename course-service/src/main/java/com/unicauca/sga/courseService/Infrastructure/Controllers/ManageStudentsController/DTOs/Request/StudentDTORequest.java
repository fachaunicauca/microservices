package com.unicauca.sga.courseService.Infrastructure.Controllers.ManageStudentsController.DTOs.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StudentDTORequest {
    private Long studentId;

    @NotBlank(message = "Debe indicar el nombre del estudiante")
    @Size(max = 1000, message = "El nombre del estudiante es demasiado largo")
    private String studentFirstName;

    @NotBlank(message = "Debe indicar el apellido del estudiante")
    @Size(max = 1000, message = "El apellido del estudiante es demasiado largo")
    private String studentLastName;

    @NotBlank(message = "Debe indicar el correo del estudiante")
    @Size(max = 1000, message = "El correo del estudiante es demasiado largo")
    private String studentEmail;
}
