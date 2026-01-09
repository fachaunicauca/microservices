package com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes;

import lombok.Data;

@Data
public class ChoiceAnswer{
    private long id;
    private String text;
    private boolean correct;
}
