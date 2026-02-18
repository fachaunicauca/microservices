package com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.DTOs;

import lombok.Data;

@Data
public class TestStatsDTOResponse {
    private int totalTaken;
    private int totalPassed;
    private int totalFailed;
    private Integer totalNotTaken;
    private double averageScore;
    private double standardDeviation;
}
