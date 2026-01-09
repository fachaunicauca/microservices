package com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures;

import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import lombok.Data;

import java.util.List;

@Data
public class MultipleChoiceStructure {
    private List<ChoiceAnswer> answers;
    private int correctAnswerCount;
}
