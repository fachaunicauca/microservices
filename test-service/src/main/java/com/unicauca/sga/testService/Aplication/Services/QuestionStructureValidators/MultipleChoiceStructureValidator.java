package com.unicauca.sga.testService.Aplication.Services.QuestionStructureValidators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Domain.Exceptions.InvalidQuestionStructureException;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureValidator;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MultipleChoiceStructureValidator implements QuestionStructureValidator {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String getSupportedType() {
        return "MULTIPLE_CHOICE";
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
                throw new InvalidQuestionStructureException("Cada opción debe tener identificador, texto e indicar si es correcta.");
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
}
