package com.unicauca.sga.testService.Domain.Models;

import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TestAttempt {
    private Long testAttemptId;
    private String studentEmail;
    private LocalDateTime testAttemptDate;
    private Integer testAttemptNumberOfQuestions;
    private Double testAttemptScore;
    private Boolean fullyScored;

    private Test test;
    private List<StudentResponse> studentResponses;
}
