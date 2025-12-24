package com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.DTOs.Response;

import lombok.Data;

@Data
public class QuestionDTOResponse {
    private long questionId;
    private String questionText;
    private String questionTitle;
    private String questionImageUrl;
    private String questionType;
    private String questionStructure;
}
