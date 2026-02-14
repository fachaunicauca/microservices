package com.unicauca.sga.testService.Infrastructure.Clients.CourseServiceClient.DTOs;

import lombok.Data;

@Data
public class CourseStudentDTOResponse {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
}
