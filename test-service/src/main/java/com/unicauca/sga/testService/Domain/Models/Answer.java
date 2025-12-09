package com.unicauca.sga.testService.Domain.Models;

import lombok.Data;

@Data
public abstract class Answer {
    private long answerId;

    private Question question;
}
