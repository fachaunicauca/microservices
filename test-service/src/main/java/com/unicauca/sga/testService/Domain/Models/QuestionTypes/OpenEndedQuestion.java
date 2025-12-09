package com.unicauca.sga.testService.Domain.Models.QuestionTypes;

import com.unicauca.sga.testService.Domain.Models.Question;

public class OpenEndedQuestion extends Question {

    @Override
    public boolean requiresManualGrade() {
        return true;
    }
}
