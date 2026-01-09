package com.unicauca.sga.testService.Domain.Models;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TestAttempt {
    private Long testAttemptId;
    private String studentEmail;
    private LocalDateTime testAttemptDate;
    private double testAttemptScore;
    private boolean fullyScored;

    private Test test;
    private List<StudentResponse> studentResponses;
}
