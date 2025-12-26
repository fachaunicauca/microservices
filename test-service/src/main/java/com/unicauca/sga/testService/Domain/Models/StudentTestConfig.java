package com.unicauca.sga.testService.Domain.Models;

import lombok.Data;

@Data
public class StudentTestConfig {
    private Long studentTestConfigId;
    private String studentEmail;
    private int attemptLimit;
    private int attemptsUsed;

    private Test test;

    public boolean hasRemainingAttempts() {
        return attemptLimit - attemptsUsed > 0;
    }

    public void registerAttempt() {
        if(!hasRemainingAttempts()){
            throw new IllegalStateException("No remaining attempts");
        }
        attemptsUsed++;
    }
}
