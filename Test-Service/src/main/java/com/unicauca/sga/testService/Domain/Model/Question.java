package com.unicauca.sga.testService.Domain.Model;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private Long question_id;
    private QuestionTopic questionTopic;
    private Subject subject;
    private String question_title;
    private String question_text;
    private String question_image;
    private List<Answer> answers;
}
