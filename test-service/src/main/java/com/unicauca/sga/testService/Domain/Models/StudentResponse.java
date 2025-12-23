package com.unicauca.sga.testService.Domain.Models;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import lombok.Data;

@Data
public class StudentResponse {
    private long studentResponseId;
    private String response;
    private int score;
    private boolean isGraded;

    private TestAttempt testAttempt;
    private Question question;
}
