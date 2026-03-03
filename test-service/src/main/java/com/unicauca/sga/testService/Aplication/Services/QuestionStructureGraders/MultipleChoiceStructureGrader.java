package com.unicauca.sga.testService.Aplication.Services.QuestionStructureGraders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Domain.Exceptions.InvalidQuestionStructureException;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureGrader;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.ResponseTypes.ChoiceResponse;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MultipleChoiceStructureGrader implements QuestionStructureGrader {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getSupportedType() {
        return "MULTIPLE_CHOICE";
    }

    @Override
    public boolean requiresManualGrade() {return false;}

    @Override
    public int grade(Question question, StudentResponse studentResponse) {
        try {
            ChoiceResponse choiceResponse = mapper.readValue(studentResponse.getResponse(), ChoiceResponse.class);
            MultipleChoiceStructure structure = mapper.readValue(question.getQuestionStructure(), MultipleChoiceStructure.class);

            Set<Long> studentChoicesIds = new HashSet<>(choiceResponse.getSelectedAnswerIds());

            if (studentChoicesIds.size() != structure.getCorrectAnswerCount()) {
                return 0;
            }

            if (structure.getCorrectAnswerCount() == 1) {
                Long studentChoice = studentChoicesIds.iterator().next();
                return structure.getAnswers().stream()
                        .anyMatch(choice -> choice.getCorrect() && Objects.equals(choice.getId(), studentChoice))
                        ? 1 : 0;
            }

            Set<Long> correctChoices = structure.getAnswers()
                    .stream()
                    .filter(ChoiceAnswer::getCorrect)
                    .map(ChoiceAnswer::getId)
                    .collect(Collectors.toSet());

            return studentChoicesIds.equals(correctChoices) ? 1 : 0;

        } catch (Exception ex) {
            System.out.println("Ocurrió un error al calificar una pregunta (Id: "+ question.getQuestionId() + ") de opción multiple:" + ex.getMessage());
            return 0;
        }
    }

    @Override
    public String cleanStructure(String questionStructure) {
        try {
            MultipleChoiceStructure structure = mapper.readValue(questionStructure, MultipleChoiceStructure.class);

            // El estudiante NO puede ver cuáles respuestas son correctas
            if (structure.getAnswers() != null) {
                structure.getAnswers().forEach(answer -> answer.setCorrect(false));
            }

            return mapper.writeValueAsString(structure);

        } catch (JsonProcessingException e) {
            throw new InvalidQuestionStructureException("Error al procesar la estructura.");
        }
    }
}

