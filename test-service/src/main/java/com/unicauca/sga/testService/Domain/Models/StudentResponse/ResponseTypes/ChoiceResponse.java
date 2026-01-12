package com.unicauca.sga.testService.Domain.Models.StudentResponse.ResponseTypes;

import lombok.Data;

import java.util.List;

@Data
public class ChoiceResponse {
    private List<Long> selectedAnswerIds;
}
