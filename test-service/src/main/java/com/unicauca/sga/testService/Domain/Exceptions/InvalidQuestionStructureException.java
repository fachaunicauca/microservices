package com.unicauca.sga.testService.Domain.Exceptions;

public class InvalidQuestionStructureException extends RuntimeException {
    public InvalidQuestionStructureException(String message) {
        super(message);
    }
}
