package com.unicauca.sga.testService.Aplication.Strategy.Question.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Domain.Exceptions.InvalidQuestionStructureException;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStrategy;
import com.unicauca.sga.testService.Domain.Models.Question.Views;
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

            if (studentChoicesIds.size() != structure.getCorrectAnswerCount()) {
                return 0;
            }

            if (structure.getCorrectAnswerCount() == 1) {
                Long studentChoice = studentChoicesIds.iterator().next();
                return structure.getAnswers().stream()
                        .anyMatch(choice -> choice.isCorrect() && choice.getId() ==  studentChoice)
                        ? 1 : 0;
            }

            Set<Long> correctChoices = structure.getAnswers()
                    .stream()
                    .filter(ChoiceAnswer::isCorrect)
                    .map(ChoiceAnswer::getId)
                    .collect(Collectors.toSet());

            return studentChoicesIds.equals(correctChoices) ? 1 : 0;

        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public String validateStructure(String json) {
        try {
            MultipleChoiceStructure structure = mapper.readValue(json, MultipleChoiceStructure.class);

            if (structure.getAnswers() == null || structure.getAnswers().size() < 2) {
                throw new InvalidQuestionStructureException("Una pregunta de opción múltiple debe tener al menos 2 opciones.");
            }

            boolean hasEmptyAnswer = structure.getAnswers().stream()
                    .anyMatch(answer -> answer.getText() == null || answer.getText().trim().isEmpty());

            if (hasEmptyAnswer) {
                throw new InvalidQuestionStructureException("Todas las opciones de respuesta deben tener texto.");
            }

            long correctCount = structure.getAnswers().stream()
                    .filter(ChoiceAnswer::isCorrect)
                    .count();

            if (correctCount == 0) {
                throw new InvalidQuestionStructureException("Debe haber al menos una respuesta marcada como correcta.");
            }

            if (correctCount >= structure.getAnswers().size()) {
                throw new InvalidQuestionStructureException("Debe haber al menos una respuesta marcada como incorrecta.");
            }

            structure.setCorrectAnswerCount((int) correctCount);

            return mapper.writeValueAsString(structure);
        } catch (JsonProcessingException e) {
            throw new InvalidQuestionStructureException("El formato de la estructura de la pregunta es inválido.");
        }
    }

    @Override
    public String cleanStructure(String questionStructure) {
        try {
            MultipleChoiceStructure structure = mapper.readValue(questionStructure, MultipleChoiceStructure.class);

            return mapper.writerWithView(Views.Student.class)
                    .writeValueAsString(structure);

        } catch (JsonProcessingException e) {
            throw new InvalidQuestionStructureException("Error al procesar la estructura.");
        }
    }
}

