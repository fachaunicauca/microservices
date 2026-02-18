package com.unicauca.sga.testService.Domain.Models.TestResults;

import lombok.Data;

@Data
public class TestStats {
    private int totalTaken;
    private int totalPassed;
    private int totalFailed;
    private Integer totalNotTaken;
    private double averageScore;
    private double standardDeviation;
}
