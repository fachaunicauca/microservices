package com.unicauca.sga.courseService.Domain.Exceptions;

public class AlreadyEnrolledException extends RuntimeException {
    public AlreadyEnrolledException(String message) {
        super(message);
    }
}
