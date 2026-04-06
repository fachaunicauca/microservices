package com.unicauca.sga.testService.servicesTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Aplication.Services.QuestionStructureValidators.MultipleChoiceStructureValidator;
import com.unicauca.sga.testService.Domain.Exceptions.InvalidQuestionStructureException;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MultipleChoiceStructureValidatorTests {

    private MultipleChoiceStructureValidator validator;

    private static final String VALID_JSON = """
            {
              "answers": [
                { "id": 1, "text": "Opción A", "correct": true  },
                { "id": 2, "text": "Opción B", "correct": false }
              ]
            }
            """;

    @BeforeEach
    void setUp() {
        validator = new MultipleChoiceStructureValidator();
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenJsonIsMalformed() {
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure("{invalid}"));
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenAnswersIsNull() {
        String json = "{ \"answers\": null }";
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure(json));
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenAnswersHasLessThanTwoOptions() {
        String json = """
                {
                  "answers": [
                    { "id": 1, "text": "Opción A", "correct": true }
                  ]
                }
                """;
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure(json));
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenAnswerIdIsNull() {
        String json = """
                {
                  "answers": [
                    { "id": null, "text": "Opción A", "correct": true  },
                    { "id": 2,    "text": "Opción B", "correct": false }
                  ]
                }
                """;
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure(json));
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenAnswerTextIsNull() {
        String json = """
                {
                  "answers": [
                    { "id": 1, "correct": true  },
                    { "id": 2, "text": "Opción B",  "correct": false }
                  ]
                }
                """;
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure(json));
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenAnswerCorrectIsNull() {
        String json = """
                {
                  "answers": [
                    { "id": 1, "text": "Opción A" },
                    { "id": 2, "text": "Opción B", "correct": false }
                  ]
                }
                """;
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure(json));
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenAnswerTextIsBlank() {
        String json = """
                {
                  "answers": [
                    { "id": 1, "text": "   ", "correct": true  },
                    { "id": 2, "text": "Opción B",   "correct": false }
                  ]
                }
                """;
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure(json));
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenAnswerIdsAreDuplicated() {
        String json = """
                {
                  "answers": [
                    { "id": 1, "text": "Opción A", "correct": true  },
                    { "id": 1, "text": "Opción B", "correct": false }
                  ]
                }
                """;
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure(json));
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenNoAnswerIsCorrect() {
        String json = """
                {
                  "answers": [
                    { "id": 1, "text": "Opción A", "correct": false },
                    { "id": 2, "text": "Opción B", "correct": false }
                  ]
                }
                """;
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure(json));
    }

    @Test
    void validateStructure_shouldThrowInvalidStructure_whenAllAnswersAreCorrect() {
        String json = """
                {
                  "answers": [
                    { "id": 1, "text": "Opción A", "correct": true },
                    { "id": 2, "text": "Opción B", "correct": true }
                  ]
                }
                """;
        assertThrows(InvalidQuestionStructureException.class, () -> validator.validateStructure(json));
    }

    @Test
    void validateStructure_shouldReturnJson_whenStructureIsValid() {
        String result = validator.validateStructure(VALID_JSON);

        assertNotNull(result);
        assertFalse(result.isBlank());
    }

    @Test
    void validateStructure_shouldSetCorrectAnswerCountToOne_whenOneAnswerIsCorrect() throws Exception {
        String result = validator.validateStructure(VALID_JSON);

        MultipleChoiceStructure parsed = new ObjectMapper().readValue(result, MultipleChoiceStructure.class);
        assertEquals(1, parsed.getCorrectAnswerCount());
    }

    @Test
    void validateStructure_shouldSetCorrectAnswerCount_whenMultipleAnswersAreCorrect() throws Exception {
        String json = """
                {
                  "answers": [
                    { "id": 1, "text": "Opción A", "correct": true  },
                    { "id": 2, "text": "Opción B", "correct": true  },
                    { "id": 3, "text": "Opción C", "correct": false }
                  ]
                }
                """;

        String result = validator.validateStructure(json);

        MultipleChoiceStructure parsed = new ObjectMapper().readValue(result, MultipleChoiceStructure.class);
        assertEquals(2, parsed.getCorrectAnswerCount());
    }
}
