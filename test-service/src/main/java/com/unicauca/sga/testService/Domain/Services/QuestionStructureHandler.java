package com.unicauca.sga.testService.Domain.Services;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;

public interface QuestionStructureHandler {
    String getSupportedType();

    boolean requiresManualGrade();

    int grade(Question question, StudentResponse studentResponse);

    String validateStructure(String questionStructure);

    String cleanStructure(String questionStructure);
}
