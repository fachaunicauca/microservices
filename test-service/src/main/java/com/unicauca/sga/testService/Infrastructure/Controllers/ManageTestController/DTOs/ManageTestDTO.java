package com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManageTestDTO {
    private int testId;
    private String teacherEmail;
    private String testTitle;
    private String testDescription;
    private int testDurationMinutes;
    private int testNumberOfQuestions;
    private int testAttemptLimit;
    private byte testState;
    private boolean isPeriodic;
}
