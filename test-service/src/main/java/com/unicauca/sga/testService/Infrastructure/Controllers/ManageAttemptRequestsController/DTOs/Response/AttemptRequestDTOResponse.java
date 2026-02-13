package com.unicauca.sga.testService.Infrastructure.Controllers.ManageAttemptRequestsController.DTOs.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttemptRequestDTOResponse {
    private Long studentTestConfigId;
    private String studentEmail;
    private int totalAttemptsUsed;
    private Double finalScore;
}
