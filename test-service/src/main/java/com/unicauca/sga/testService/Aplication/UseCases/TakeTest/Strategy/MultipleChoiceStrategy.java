package com.unicauca.sga.testService.Aplication.UseCases.TakeTest.Strategy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;
import com.unicauca.sga.testService.Domain.Models.StudentResponse;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MultipleChoiceStrategy implements QuestionStrategy {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getSupportedType() {
        return "MULTIPLE_CHOICE";
    }

    @Override
    public int grade(Question question, StudentResponse studentResponse) {
        try {
            Set<Long> studentChoicesIds = mapper.readValue(studentResponse.getResponse(), new TypeReference<>() {});
            MultipleChoiceStructure structure = mapper.readValue(question.getQuestionStructure(), MultipleChoiceStructure.class);

            if(studentChoicesIds.size() != structure.getCorrectAnswerCount()){
                return 0;
            }

            Set<Long> correctChoices = structure.getAnswers()
                    .stream()
                    .filter(ChoiceAnswer::isCorrect)
                    .map(ChoiceAnswer::getId)
                    .collect(Collectors.toSet());

            if(studentChoicesIds.size() != correctChoices.size()){
                return 0;
            }

            return studentChoicesIds.equals(correctChoices) ? 1 : 0;
        }catch (Exception ex){
            return 0;
        }
    }
}

