package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response;

import lombok.Data;

@Data
public class TakeTestQuestionDTOResponse {
    private Long questionId;
    private String questionText;
    private String questionTitle;
    private String questionImageUrl;
    private String questionType;
    private String questionStructure;
}
