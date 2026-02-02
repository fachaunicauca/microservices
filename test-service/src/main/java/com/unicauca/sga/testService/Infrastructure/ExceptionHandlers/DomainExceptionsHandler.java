package com.unicauca.sga.testService.Infrastructure.ExceptionHandlers;

import com.unicauca.sga.testService.Domain.Exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class DomainExceptionsHandler {
    @ExceptionHandler(NoQuestionsException.class)
    public ResponseEntity<String> handleNoQuestionsException(NoQuestionsException ex){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ex.getMessage());
    }

    @ExceptionHandler(InsufficientQuestionsException.class)
    public ResponseEntity<String> handleInsufficientQuestionsException(InsufficientQuestionsException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidQuestionStructureException.class)
    public ResponseEntity<String> handleInvalidQuestionStructureException(InvalidQuestionStructureException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ProtectedTestException.class)
    public ResponseEntity<String> handleProtectedTestException(ProtectedTestException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(AttemptNotAllowedException.class)
    public ResponseEntity<Map<String, String>> handleAttemptNotAllowedException(AttemptNotAllowedException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("code", ex.getCode(),"message", ex.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<String> handleAlreadyExistsException(AlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(InactiveTestException.class)
    public ResponseEntity<String> handleInactiveTestException(InactiveTestException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(HasRemainingAttemptsException.class)
    public ResponseEntity<String> handleHasRemainingAttemptsException(HasRemainingAttemptsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
