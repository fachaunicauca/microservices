package com.unicauca.sga.testService.Domain.Model;

import lombok.Data;

@Data
public class Answer {
    private Long answer_id;
    private Question question;
    private String answer_text;
    private boolean answer_isCorrect;
}
