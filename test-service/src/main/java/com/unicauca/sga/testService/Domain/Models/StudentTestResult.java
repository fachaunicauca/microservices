package com.unicauca.sga.testService.Domain.Models;

import lombok.Data;

@Data
public class StudentTestResult {
    private Long studentId;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private Integer totalAttemptsUsed;
    private Double finalScore;
}
