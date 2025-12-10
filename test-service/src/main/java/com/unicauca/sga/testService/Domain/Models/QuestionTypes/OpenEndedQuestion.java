package com.unicauca.sga.testService.Domain.Models.QuestionTypes;

import com.unicauca.sga.testService.Domain.Models.Question;

public class OpenEndedQuestion extends Question<String> {

    @Override
    public int grade(String studentResponse) {
        return 0;
    }

    @Override
    public boolean requiresManualGrade() {
        return true;
    }
}
