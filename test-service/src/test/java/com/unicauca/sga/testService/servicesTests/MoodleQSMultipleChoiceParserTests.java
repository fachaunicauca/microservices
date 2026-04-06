package com.unicauca.sga.testService.servicesTests;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionStructureParsers.MoodleQSMultipleChoiceParser;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MoodleQSMultipleChoiceParserTests {

    private MoodleQSMultipleChoiceParser parser;

    @BeforeEach
    void setUp() {
        parser = new MoodleQSMultipleChoiceParser();
    }

    // ==================== parseMoodleStructure ====================

    private Element buildQuestionElement(String... answerFractions) throws Exception {
        StringBuilder xml = new StringBuilder("<question type=\"multichoice\">");
        for (int i = 0; i < answerFractions.length; i++) {
            xml.append("<answer fraction=\"").append(answerFractions[i]).append("\">")
                    .append("<text>Opción ").append((char) ('A' + i)).append("</text>")
                    .append("</answer>");
        }
        xml.append("</question>");

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.toString().getBytes(StandardCharsets.UTF_8)));
        return doc.getDocumentElement();
    }

    @Test
    void parseMoodleStructure_shouldReturnValidJson_withSingleCorrectAnswer() throws Exception {
        Element questionEl = buildQuestionElement("100", "0", "0");

        String result = parser.parseMoodleStructure(questionEl);

        assertNotNull(result);
        MultipleChoiceStructure structure = new ObjectMapper().readValue(result, MultipleChoiceStructure.class);
        assertEquals(1, structure.getCorrectAnswerCount());
        assertEquals(3, structure.getAnswers().size());
    }

    @Test
    void parseMoodleStructure_shouldReturnValidJson_withMultipleCorrectAnswers() throws Exception {
        Element questionEl = buildQuestionElement("100", "100", "0");

        String result = parser.parseMoodleStructure(questionEl);

        assertNotNull(result);
        MultipleChoiceStructure structure = new ObjectMapper().readValue(result, MultipleChoiceStructure.class);
        assertEquals(2, structure.getCorrectAnswerCount());
    }

    @Test
    void parseMoodleStructure_shouldMarkCorrectAndIncorrectAnswers() throws Exception {
        Element questionEl = buildQuestionElement("100", "0");

        String result = parser.parseMoodleStructure(questionEl);

        MultipleChoiceStructure structure = new ObjectMapper().readValue(result, MultipleChoiceStructure.class);
        assertTrue(structure.getAnswers().get(0).getCorrect());
        assertFalse(structure.getAnswers().get(1).getCorrect());
    }

    @Test
    void parseMoodleStructure_shouldAssignSequentialIdsStartingFromOne() throws Exception {
        Element questionEl = buildQuestionElement("100", "0", "0");

        String result = parser.parseMoodleStructure(questionEl);

        MultipleChoiceStructure structure = new ObjectMapper().readValue(result, MultipleChoiceStructure.class);
        List<ChoiceAnswer> answers = structure.getAnswers();
        assertEquals(1L, answers.get(0).getId());
        assertEquals(2L, answers.get(1).getId());
        assertEquals(3L, answers.get(2).getId());
    }

    @Test
    void parseMoodleStructure_shouldReturnZeroCorrectCount_whenNoAnswerIsCorrect() throws Exception {
        Element questionEl = buildQuestionElement("0", "0");

        String result = parser.parseMoodleStructure(questionEl);

        MultipleChoiceStructure structure = new ObjectMapper().readValue(result, MultipleChoiceStructure.class);
        assertEquals(0, structure.getCorrectAnswerCount());
    }

    // ==================== parseQuestionStructure ====================

    private Question buildQuestion(String structure) {
        Question question = new Question();
        question.setQuestionStructure(structure);
        return question;
    }

    private static final String SINGLE_CORRECT_STRUCTURE = """
            {
              "answers": [
                { "id": 1, "text": "Opción A", "correct": true  },
                { "id": 2, "text": "Opción B", "correct": false }
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

    @Test
    void parseQuestionStructure_shouldReturnNull_whenStructureIsInvalidJson() {
        Question question = buildQuestion("{invalid}");

        assertNull(parser.parseQuestionStructure(question));
    }

    @Test
    void parseQuestionStructure_shouldSetSingleTrue_whenOneCorrectAnswer() {
        String result = parser.parseQuestionStructure(buildQuestion(SINGLE_CORRECT_STRUCTURE));

        assertNotNull(result);
        assertTrue(result.contains("<single>true</single>"));
    }

    @Test
    void parseQuestionStructure_shouldSetSingleFalse_whenMultipleCorrectAnswers() {
        String result = parser.parseQuestionStructure(buildQuestion(MULTI_CORRECT_STRUCTURE));

        assertNotNull(result);
        assertTrue(result.contains("<single>false</single>"));
    }

    @Test
    void parseQuestionStructure_shouldIncludeShuffleAndNumberingConfig() {
        String result = parser.parseQuestionStructure(buildQuestion(SINGLE_CORRECT_STRUCTURE));

        assertNotNull(result);
        assertTrue(result.contains("<shuffleanswers>true</shuffleanswers>"));
        assertTrue(result.contains("<answernumbering>abc</answernumbering>"));
    }

    @Test
    void parseQuestionStructure_shouldAssignFraction100_toCorrectAnswerWithSingleChoice() {
        String result = parser.parseQuestionStructure(buildQuestion(SINGLE_CORRECT_STRUCTURE));

        assertTrue(result.contains("fraction=\"100.0\""));
    }

    @Test
    void parseQuestionStructure_shouldAssignFraction50_toEachCorrectAnswerWithTwoCorrect() {
        String result = parser.parseQuestionStructure(buildQuestion(MULTI_CORRECT_STRUCTURE));

        assertTrue(result.contains("fraction=\"50.0\""));
    }

    @Test
    void parseQuestionStructure_shouldAssignFraction0_toIncorrectAnswers() {
        String result = parser.parseQuestionStructure(buildQuestion(SINGLE_CORRECT_STRUCTURE));

        assertTrue(result.contains("fraction=\"0\"") || result.contains("fraction=\"0.0\""));
    }
}
