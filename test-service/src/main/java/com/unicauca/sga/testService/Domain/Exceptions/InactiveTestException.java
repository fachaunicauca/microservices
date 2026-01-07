package com.unicauca.sga.testService.Domain.Exceptions;

public class InactiveTestException extends RuntimeException {
    public InactiveTestException(String message) {
        super(message);
    }
}
