package com.unicauca.sga.testService.Domain.Models.QuestionTypes;

import com.unicauca.sga.testService.Domain.Models.Answer;
import com.unicauca.sga.testService.Domain.Models.Question;

import java.util.List;

public class MultipleChoiceQuestion extends Question {
    private List<Long> correctAnswersIds;

    public int grade(Answer answer) {
        return 0;
    }

    @Override
    public boolean requiresManualGrade() {
        return false;
    }
}
