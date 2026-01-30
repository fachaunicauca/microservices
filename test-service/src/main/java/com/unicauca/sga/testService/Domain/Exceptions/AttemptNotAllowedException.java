package com.unicauca.sga.testService.Domain.Exceptions;

import com.unicauca.sga.testService.Domain.Enums.AttemptNotAllowedCode;

public class AttemptNotAllowedException extends RuntimeException {

    private final AttemptNotAllowedCode code;

    public AttemptNotAllowedException(AttemptNotAllowedCode code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode(){
        return code.toString();
    }
}
