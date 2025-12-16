package com.unicauca.sga.testService.Domain.Models;

import lombok.Data;

import java.util.List;

@Data
public class Test {
    private int testId;
    private String teacherEmail;
    private String testTitle;
    private String testDescription;
    private int testDurationMinutes;
    private int testNumberOfQuestions;
    private int testAttemptLimit;
    private byte testState;
    private boolean isPeriodic;

    private List<Question<?>> questions;

    public boolean hasEnoughQuestion(int totalQuestions){
        return totalQuestions >= this.testNumberOfQuestions;
    }
}
