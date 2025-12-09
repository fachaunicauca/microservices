package com.unicauca.sga.testService.Domain.Models.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class StudentAnswerDTO {
    private long questionId;
    private List<Long> answersIds;
}
