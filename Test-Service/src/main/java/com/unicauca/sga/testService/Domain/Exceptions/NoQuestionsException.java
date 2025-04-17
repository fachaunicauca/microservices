package com.unicauca.sga.testService.Domain.Exceptions;

public class NoQuestionsException extends RuntimeException{
    public NoQuestionsException(String message){
        super(message);
    }
    public NoQuestionsException(String message, Throwable cause){
        super(message, cause);
    }
}
