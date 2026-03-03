package com.unicauca.sga.testService.Domain.Services;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;

public interface QuestionStructureGrader {
    String getSupportedType();

    boolean requiresManualGrade();

    int grade(Question question, StudentResponse studentResponse);

    String cleanStructure(String questionStructure);
}
