package com.unicauca.sga.testService.Aplication.UseCases.TakeTest.Strategy;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.StudentResponse;

public interface QuestionStrategy {
    String getSupportedType();

    int grade(Question question, StudentResponse studentResponse);
}
