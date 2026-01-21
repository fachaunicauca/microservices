package com.unicauca.sga.testService.Domain.Models.StudentResponse;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import lombok.Data;

@Data
public class StudentResponse {
    private Long studentResponseId;
    private String response;
    private int score;
    private boolean isGraded;

    private TestAttempt testAttempt;
    private Long questionId;
}
