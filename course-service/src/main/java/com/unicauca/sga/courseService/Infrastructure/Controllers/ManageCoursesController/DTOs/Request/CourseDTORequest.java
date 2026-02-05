package com.unicauca.sga.courseService.Infrastructure.Controllers.ManageCoursesController.DTOs.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseDTORequest {
    private Integer courseId;

    @NotBlank(message = "Debe indicar el nombre del curso")
    @Size(max = 1000, message = "El nombre del curso puede tener como máximo 1000 caracteres")
    private String courseName;

    @NotBlank(message = "Debe indicar el correo del docente que dicta el curso")
    private String teacherEmail;

    @Size(max = 5000, message = "La descripción del curso puede tener como máximo 5000 caracteres")
    private String courseDescription;
}
