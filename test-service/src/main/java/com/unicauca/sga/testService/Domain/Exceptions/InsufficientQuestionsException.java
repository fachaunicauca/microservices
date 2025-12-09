package com.unicauca.sga.testService.Domain.Exceptions;

public class InsufficientQuestionsException extends RuntimeException{
    public InsufficientQuestionsException(String message){
        super(message);
    }
}
