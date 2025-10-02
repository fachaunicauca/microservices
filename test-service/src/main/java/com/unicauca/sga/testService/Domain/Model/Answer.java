package com.unicauca.sga.testService.Domain.Model;

import lombok.Data;

@Data
public class Answer {
    private Long answerId;
    private Question question;
    private String answerText;
    private boolean correct;
}
