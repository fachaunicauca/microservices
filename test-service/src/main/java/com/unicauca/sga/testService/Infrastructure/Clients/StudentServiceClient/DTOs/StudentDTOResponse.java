package com.unicauca.sga.testService.Infrastructure.Clients.StudentServiceClient.DTOs;

import lombok.Data;

@Data
public class StudentDTOResponse {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
}
