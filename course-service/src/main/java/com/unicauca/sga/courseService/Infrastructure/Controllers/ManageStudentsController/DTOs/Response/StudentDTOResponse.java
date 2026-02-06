package com.unicauca.sga.courseService.Infrastructure.Controllers.ManageStudentsController.DTOs.Response;

import lombok.Data;

@Data
public class StudentDTOResponse {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
}
