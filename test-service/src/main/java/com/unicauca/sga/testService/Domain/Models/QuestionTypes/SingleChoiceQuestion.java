package com.unicauca.sga.testService.Domain.Models.QuestionTypes;

import com.unicauca.sga.testService.Domain.Models.Answer;
import com.unicauca.sga.testService.Domain.Models.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question;

import java.util.List;

public class SingleChoiceQuestion extends Question<ChoiceAnswer> {
    private List<Answer> answers;

    @Override
    public int grade(ChoiceAnswer answer) {
        return 0;
    }

    @Override
    public boolean requiresManualGrade() {
        return false;
    }
}
