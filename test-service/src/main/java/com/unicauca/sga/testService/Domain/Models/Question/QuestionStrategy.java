package com.unicauca.sga.testService.Domain.Models.Question;

import com.unicauca.sga.testService.Domain.Models.StudentResponse;

public interface QuestionStrategy {
    String getSupportedType();

    int grade(Question question, StudentResponse studentResponse);

    String validateStructure(String questionStructure);

    String cleanStructure(String questionStructure);
}
