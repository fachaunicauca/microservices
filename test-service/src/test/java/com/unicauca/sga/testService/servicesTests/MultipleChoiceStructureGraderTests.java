package com.unicauca.sga.testService.servicesTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Aplication.Services.QuestionStructureGraders.MultipleChoiceStructureGrader;
import com.unicauca.sga.testService.Domain.Exceptions.InvalidQuestionStructureException;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;
import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MultipleChoiceStructureGraderTests {

    private MultipleChoiceStructureGrader grader;

    @Mock
    private Question question;

    @Mock
    private StudentResponse studentResponse;

    private static final String SINGLE_CORRECT_STRUCTURE = """
            {
              "answers": [
                { "id": 1, "text": "Opción A", "correct": true  },
                { "id": 2, "text": "Opción B", "correct": false },
                { "id": 3, "text": "Opción C", "correct": false }
              ],
              "correctAnswerCount": 1
            }
            """;

    private static final String MULTI_CORRECT_STRUCTURE = """
            {
              "answers": [
                { "id": 1, "text": "Opción A", "correct": true  },
                { "id": 2, "text": "Opción B", "correct": true  },
                { "id": 3, "text": "Opción C", "correct": false }
              ],
              "correctAnswerCount": 2
            }
            """;

    @BeforeEach
    void setUp() {
        grader = new MultipleChoiceStructureGrader();
    }

    // ==================== grade ====================

    @Test
    void grade_shouldReturnZero_whenStudentResponseIsInvalidJson() {
        when(studentResponse.getResponse()).thenReturn("{}");

        assertEquals(0, grader.grade(question, studentResponse));
    }

    @Test
    void grade_shouldReturnZero_whenQuestionStructureIsInvalidJson() {
        when(question.getQuestionStructure()).thenReturn("{}");
        when(studentResponse.getResponse()).thenReturn("{ \"selectedAnswerIds\": [1] }");

        assertEquals(0, grader.grade(question, studentResponse));
    }

    @Test
    void grade_shouldReturnZero_whenStudentSelectsMoreIdsThanCorrectCount() {
        when(question.getQuestionStructure()).thenReturn(SINGLE_CORRECT_STRUCTURE);
        when(studentResponse.getResponse()).thenReturn(" {\"selectedAnswerIds\": [1, 2] }");

        assertEquals(0, grader.grade(question, studentResponse));
    }

    @Test
    void grade_shouldReturnZero_whenStudentSelectsFewerIdsThanCorrectCount() {
        when(question.getQuestionStructure()).thenReturn(MULTI_CORRECT_STRUCTURE);
        when(studentResponse.getResponse()).thenReturn("{ \"selectedAnswerIds\": [1] }");

        assertEquals(0, grader.grade(question, studentResponse));
    }

    @Test
    void grade_shouldReturnOne_whenStudentSelectsTheCorrectSingleAnswer() {
        when(question.getQuestionStructure()).thenReturn(SINGLE_CORRECT_STRUCTURE);
        when(studentResponse.getResponse()).thenReturn("{ \"selectedAnswerIds\": [1] }");

        assertEquals(1, grader.grade(question, studentResponse));
    }

    @Test
    void grade_shouldReturnZero_whenStudentSelectsWrongSingleAnswer() {
        when(question.getQuestionStructure()).thenReturn(SINGLE_CORRECT_STRUCTURE);
        when(studentResponse.getResponse()).thenReturn("{ \"selectedAnswerIds\": [2] }");

        assertEquals(0, grader.grade(question, studentResponse));
    }

    @Test
    void grade_shouldReturnOne_whenStudentSelectsAllCorrectAnswers() {
        when(question.getQuestionStructure()).thenReturn(MULTI_CORRECT_STRUCTURE);
        when(studentResponse.getResponse()).thenReturn(" {\"selectedAnswerIds\": [1, 2] }");

        assertEquals(1, grader.grade(question, studentResponse));
    }

    @Test
    void grade_shouldReturnZero_whenStudentSelectsOnlyOneOfMultipleCorrectAnswers() {
        when(question.getQuestionStructure()).thenReturn(MULTI_CORRECT_STRUCTURE);
        when(studentResponse.getResponse()).thenReturn(" {\"selectedAnswerIds\": [1, 3] }");

        assertEquals(0, grader.grade(question, studentResponse));
    }

    @Test
    void grade_shouldReturnZero_whenStudentSelectsWrongAnswersWithCorrectCount() {
        when(question.getQuestionStructure()).thenReturn(MULTI_CORRECT_STRUCTURE);
        when(studentResponse.getResponse()).thenReturn(" {\"selectedAnswerIds\": [2, 3] }");

        assertEquals(0, grader.grade(question, studentResponse));
    }

    // ==================== cleanStructure ====================

    @Test
    void cleanStructure_shouldThrowInvalidStructure_whenStructureIsInvalidJson() {
        assertThrows(InvalidQuestionStructureException.class, () -> grader.cleanStructure("{invalid}"));
    }

    @Test
    void cleanStructure_shouldSetAllAnswersCorrectToFalse() throws JsonProcessingException {
        String result = grader.cleanStructure(SINGLE_CORRECT_STRUCTURE);

        MultipleChoiceStructure parsed = new ObjectMapper().readValue(result, MultipleChoiceStructure.class);
        boolean anyCorrect = parsed.getAnswers().stream().anyMatch(ChoiceAnswer::getCorrect);

        assertFalse(anyCorrect);
    }
}
