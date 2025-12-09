package com.unicauca.sga.testService.Domain.Models.QuestionTypes;

import com.unicauca.sga.testService.Domain.Models.Answer;
import com.unicauca.sga.testService.Domain.Models.Question;

public class SingleChoiceQuestion extends Question {
    private long correctAnswerId;

    public int grade(Answer answer) {
        return 0;
    }

    @Override
    public boolean requiresManualGrade() {
        return false;
    }
}
