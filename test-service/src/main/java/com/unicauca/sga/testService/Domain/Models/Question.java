package com.unicauca.sga.testService.Domain.Models;

import lombok.Data;

@Data
public abstract class Question {
    private long questionId;
    private String questionText;
    private String questionTitle;
    private byte[] questionImage;

    private Test test;

    public abstract boolean requiresManualGrade();
}
