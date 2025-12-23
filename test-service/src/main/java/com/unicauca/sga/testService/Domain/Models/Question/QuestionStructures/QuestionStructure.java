package com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures;

public interface QuestionStructure {
    QuestionStructure getCleanCopy();
    boolean requiresManualGrade();
}
