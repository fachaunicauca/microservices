package com.unicauca.sga.testService.Domain.Model;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private Long questionId;
    private QuestionTopic questionTopic;
    private Subject subject;
    private String questionTitle;
    private String questionText;
    private List<Answer> answers;
}
