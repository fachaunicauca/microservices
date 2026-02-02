package com.unicauca.sga.testService.Domain.Exceptions;

public class HasRemainingAttemptsException extends RuntimeException {
    public HasRemainingAttemptsException(String message) {
        super(message);
    }
}
