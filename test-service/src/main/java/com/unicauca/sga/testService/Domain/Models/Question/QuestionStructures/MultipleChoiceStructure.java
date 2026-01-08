package com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.Views;
import lombok.Data;

import java.util.List;

@Data
public class MultipleChoiceStructure {
    @JsonView(Views.Student.class)
    private List<ChoiceAnswer> answers;
    @JsonView(Views.Teacher.class)
    private int correctAnswerCount;
}
