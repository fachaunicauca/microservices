package com.unicauca.sga.testService.Domain.Services;

public interface QuestionStructureValidator {
    String getSupportedType();

    String validateStructure(String questionStructure);
}
