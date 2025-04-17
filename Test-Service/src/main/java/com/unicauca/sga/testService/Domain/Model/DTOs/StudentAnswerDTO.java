package com.unicauca.sga.testService.Domain.Model.DTOs;

import lombok.Data;

import java.util.List;

@Data
public class StudentAnswerDTO {
    private long question_id;
    private List<Long> answers_ids;
}
