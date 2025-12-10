package com.unicauca.sga.testService.Domain.Models.QuestionTypes;

import com.unicauca.sga.testService.Domain.Models.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question;

import java.util.List;

public class MultipleChoiceQuestion extends Question<List<ChoiceAnswer>> {
    private List<ChoiceAnswer> answers;

    @Override
    public int grade(List<ChoiceAnswer> studentResponse) {
        return 0;
    }

    @Override
    public boolean requiresManualGrade() {
        return false;
    }
}
