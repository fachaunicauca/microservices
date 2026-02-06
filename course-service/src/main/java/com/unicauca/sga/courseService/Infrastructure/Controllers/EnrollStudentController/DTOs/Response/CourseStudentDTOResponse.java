package com.unicauca.sga.courseService.Infrastructure.Controllers.EnrollStudentController.DTOs.Response;

import lombok.Data;

@Data
public class CourseStudentDTOResponse {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
}
