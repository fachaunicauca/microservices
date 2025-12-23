package com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures;

import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.OpenEndedAnswer;
import lombok.Data;

@Data
public class OpenEndedStructure implements QuestionStructure {
    private OpenEndedAnswer response;

    @Override
    public QuestionStructure getCleanCopy() {
        return new OpenEndedStructure();
    }

    @Override
    public boolean requiresManualGrade() {
        return true;
    }
}
