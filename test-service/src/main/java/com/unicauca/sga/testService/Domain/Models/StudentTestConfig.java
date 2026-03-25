package com.unicauca.sga.testService.Domain.Models;

import com.unicauca.sga.testService.Domain.Enums.AttemptRequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentTestConfig {
    private Long studentTestConfigId;
    private String studentEmail;
    private int attemptsUsed;
    private int totalAttemptsUsed;
    private LocalDateTime lastAttemptAt;
    private Double finalScore;
    private AttemptRequestStatus attemptRequestStatus = AttemptRequestStatus.NOT_REQUESTED;

    private Test test;

    public StudentTestConfig(String studentEmail,
                             Test test) {
        this.studentEmail = studentEmail;
        this.test = test;
        // El lastAttemptAt y finalScore se inicializan en null
    }

    public int getRemainingAttempts(){
        return  test.getTestAttemptLimit() - attemptsUsed;
    }

    public void incrementAttemptsUsed(){
        this.attemptsUsed++;
        this.totalAttemptsUsed++;
    }

    public boolean hasRemainingAttempts() {
        return attemptsUsed < test.getTestAttemptLimit();
    }

    public boolean hasAlreadyPassed(double passingScore) {
        return finalScore != null && finalScore >= passingScore;
    }

}
