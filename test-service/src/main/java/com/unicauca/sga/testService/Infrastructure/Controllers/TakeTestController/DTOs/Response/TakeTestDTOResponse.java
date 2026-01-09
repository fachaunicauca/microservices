package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response;

import lombok.Data;

import java.util.List;

@Data
public class TakeTestDTOResponse {
    private Integer testId;
    private String testTitle;
    private int testDurationMinutes;
    private int testNumberOfQuestions;

    private List<TakeTestQuestionDTOResponse> questions;
}
