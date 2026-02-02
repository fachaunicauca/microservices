package com.unicauca.sga.testService.Infrastructure.Controllers.ManageStudentTestConfigController.DTOs.Response;

import com.unicauca.sga.testService.Domain.Enums.AttemptRequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentTestConfigDTOResponse {
    private Long studentTestConfigId;
    private String studentEmail;
    private int attemptLimit;
    private int attemptsUsed;
    private LocalDateTime lastAttemptAt;
    private Double finalScore;
    private String attemptRequestStatus;
}
