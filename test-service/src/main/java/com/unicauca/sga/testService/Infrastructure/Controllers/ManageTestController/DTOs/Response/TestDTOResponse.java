package com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDTOResponse {
    private int testId;
    private String teacherEmail;
    private String testTitle;
    private String testDescription;
    private int testDurationMinutes;
    private int testNumberOfQuestions;
    private int testAttemptLimit;
    private byte testState;
    @JsonProperty("isPeriodic")
    private boolean isPeriodic;
}
