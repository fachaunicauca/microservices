package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private long question_id;
    private String question_title;
    private String question_text;
    private String question_image;
    private List<AnswerDTO> answers;
}
