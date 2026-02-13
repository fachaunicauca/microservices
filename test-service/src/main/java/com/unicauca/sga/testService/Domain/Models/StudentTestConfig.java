package com.unicauca.sga.testService.Domain.Models;

import com.unicauca.sga.testService.Domain.Enums.AttemptRequestStatus;
import lombok.AllArgsConstructor;
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

    public boolean isSameSemester() {
        if (lastAttemptAt == null) return false;

        return resolveSemester(lastAttemptAt)
                .equals(resolveSemester(LocalDateTime.now()));
    }

    public boolean hasAlreadyPassed(double passingScore) {
        if (test.isPeriodic()) {
            return hasPassedCurrentSemester(passingScore);
        }
        return hasPassed(passingScore);
    }

    public boolean hasPassed(double passingScore) {
        return finalScore != null && finalScore >= passingScore;
    }

    public boolean hasPassedCurrentSemester(double passingScore) {
        if (finalScore == null || lastAttemptAt == null) return false;

        String lastSemester = resolveSemester(lastAttemptAt);
        String currentSemester = resolveSemester(LocalDateTime.now());

        return lastSemester.equals(currentSemester) && finalScore >= passingScore;
    }

    private String resolveSemester(LocalDateTime date) {
        int year = date.getYear();
        int semester = date.getMonthValue() <= 6 ? 1 : 2;
        return year + "-" + semester;
    }

}
