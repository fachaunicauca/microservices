package com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes;

import lombok.Data;

@Data
public class ChoiceAnswer{
    private Long id;
    private String text;
    private Boolean correct;
}
