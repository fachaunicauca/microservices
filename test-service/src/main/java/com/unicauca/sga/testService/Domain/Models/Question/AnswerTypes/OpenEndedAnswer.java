package com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes;

import lombok.Data;

@Data
public class OpenEndedAnswer {
    private String studentEmail;
    private String response;
}
