package com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.DTOs;

import lombok.Data;

@Data
public class StudentsResultsDTOResponse {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private Integer totalAttemptsUsed;
    private Double finalScore;
}
