package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private long questionId;
    private String questionTitle;
    private String questionText;
    private List<AnswerDTO> answers;
}
