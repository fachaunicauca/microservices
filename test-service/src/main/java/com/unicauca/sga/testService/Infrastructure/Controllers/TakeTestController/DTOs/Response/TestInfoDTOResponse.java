package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response;

import lombok.Data;

@Data
public class TestInfoDTOResponse {
    private Integer testId;
    private String testTitle;
    private String testDescription;
    private String teacherEmail;
}
