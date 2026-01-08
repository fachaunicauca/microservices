package com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicauca.sga.testService.Domain.Models.Question.Views;
import lombok.Data;

@Data
public class OpenEndedStructure {
    @JsonView(Views.Student.class)
    private int maxResponseSize;
}
