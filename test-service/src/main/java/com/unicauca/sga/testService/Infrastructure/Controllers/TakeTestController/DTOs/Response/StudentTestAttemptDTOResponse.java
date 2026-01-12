package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentTestAttemptDTOResponse {
    private String studentEmail;
    private LocalDateTime testAttemptDate;
    private Integer testAttemptNumberOfQuestions;
    private Double testAttemptScore;
    private Boolean fullyScored;
}
