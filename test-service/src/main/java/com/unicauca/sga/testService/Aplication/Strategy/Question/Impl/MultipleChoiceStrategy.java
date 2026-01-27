package com.unicauca.sga.testService.Aplication.Strategy.Question.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Domain.Exceptions.InvalidQuestionStructureException;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStrategy;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.ResponseTypes.ChoiceResponse;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
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
    public String validateStructure(String json) {
        try {
            MultipleChoiceStructure structure = mapper.readValue(json, MultipleChoiceStructure.class);

            if (structure.getAnswers() == null || structure.getAnswers().size() < 2) {
                throw new InvalidQuestionStructureException("Una pregunta de opción múltiple debe tener al menos 2 opciones.");
            }

            boolean hasInvalidAnswer = structure.getAnswers().stream()
                    .anyMatch(answer ->
                            answer.getId() == null ||
                            answer.getText() == null ||
                            answer.getText().trim().isEmpty() ||
                            answer.getCorrect() == null
                    );

            if (hasInvalidAnswer) {
                throw new InvalidQuestionStructureException("Cada opción debe tener identificador id, texto y si es correcta.");
            }

            Set<Long> ids = structure.getAnswers().stream()
                    .map(ChoiceAnswer::getId)
                    .collect(Collectors.toSet());

            if (ids.size() != structure.getAnswers().size()) {
                throw new InvalidQuestionStructureException("Los identificadores (id) de las opciones deben ser únicos.");
            }

            long correctCount = structure.getAnswers().stream()
                    .filter(answer -> Boolean.TRUE.equals(answer.getCorrect()))
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

