package com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures;

import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import lombok.Data;

import java.util.List;

@Data
public class MultipleChoiceStructure implements QuestionStructure {
    private List<ChoiceAnswer> answers;
    private int correctAnswerCount;

    @Override
    public QuestionStructure getCleanCopy() {
        MultipleChoiceStructure cleanCopy = new MultipleChoiceStructure();

        cleanCopy.setAnswers(this.answers.stream().map(ch -> {
            ChoiceAnswer cleanAnswer = new ChoiceAnswer();
            cleanAnswer.setId(ch.getId());
            cleanAnswer.setText(ch.getText());
            return cleanAnswer;
        }).toList());

        cleanCopy.setCorrectAnswerCount(this.correctAnswerCount);

        return cleanCopy;
    }

    @Override
    public boolean requiresManualGrade() {
        return false;
    }
}
