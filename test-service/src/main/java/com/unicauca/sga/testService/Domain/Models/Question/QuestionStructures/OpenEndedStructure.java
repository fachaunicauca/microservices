package com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures;

import lombok.Data;

@Data
public class OpenEndedStructure implements QuestionStructure {
    private int maxResponseSize;

    @Override
    public QuestionStructure createCleanCopy() {
        return this;
    }

    @Override
    public boolean requiresManualGrade() {
        return true;
    }
}
