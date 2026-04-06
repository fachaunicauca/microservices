package com.unicauca.sga.testService.servicesTests;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Infrastructure.Services.MoodleQStructureParserRegistry;
import com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionParser;
import com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionStructureParsers.MoodleQStructureParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoodleQuestionParserTests {

    @Mock
    private MoodleQStructureParserRegistry parsersRegistry;

    @Mock
    private MoodleQStructureParser structureParser;

    private MoodleQuestionParser parser;

    private static final String VALID_XML_MULTICHOICE = """
            <?xml version="1.0" encoding="UTF-8"?>
            <quiz>
                <question type="multichoice">
                    <name><text>Pregunta 1</text></name>
                    <questiontext><text>¿Cuál es la capital de Francia?</text></questiontext>
                    <answer fraction="100"><text>París</text></answer>
                    <answer fraction="0"><text>Madrid</text></answer>
                </question>
            </quiz>
            """;

    private static final String VALID_XML_TWO_MULTICHOICE = """
            <?xml version="1.0" encoding="UTF-8"?>
            <quiz>
                <question type="multichoice">
                    <name><text>Pregunta 1</text></name>
                    <questiontext><text>Texto 1</text></questiontext>
                </question>
                <question type="multichoice">
                    <name><text>Pregunta 2</text></name>
                    <questiontext><text>Texto 2</text></questiontext>
                </question>
            </quiz>
            """;

    private static final String VALID_XML_UNSUPPORTED_TYPE = """
            <?xml version="1.0" encoding="UTF-8"?>
            <quiz>
                <question type="notsupported">
                    <name><text>Pregunta invalida</text></name>
                    <questiontext><text>Texto</text></questiontext>
                </question>
            </quiz>
            """;

    private static final String INVALID_XML = "<<>>";

    @BeforeEach
    void setUp() {
        parser = new MoodleQuestionParser(parsersRegistry);
    }

    // ==================== parseMoodleQuestions ====================

    @Test
    void parseMoodleQuestions_shouldThrowResponseStatusException_whenXmlIsInvalid() {
        InputStream stream = new ByteArrayInputStream(INVALID_XML.getBytes(StandardCharsets.UTF_8));

        assertThrows(ResponseStatusException.class, () -> parser.parseMoodleQuestions(stream, List.of(1), 1));
    }

    @Test
    void parseMoodleQuestions_shouldReturnEmptyList_whenNoIndexIsSelected() {
        InputStream stream = new ByteArrayInputStream(VALID_XML_MULTICHOICE.getBytes(StandardCharsets.UTF_8));

        List<Question> result = parser.parseMoodleQuestions(stream, List.of(), 1);

        assertTrue(result.isEmpty());
    }

    @Test
    void parseMoodleQuestions_shouldReturnEmptyList_whenQuestionTypeIsNotSupported() {
        when(parsersRegistry.hasSupport("notsupported")).thenReturn(false);

        InputStream stream = new ByteArrayInputStream(VALID_XML_UNSUPPORTED_TYPE.getBytes(StandardCharsets.UTF_8));

        List<Question> result = parser.parseMoodleQuestions(stream, List.of(1), 1);

        assertTrue(result.isEmpty());
    }

    @Test
    void parseMoodleQuestions_shouldReturnEmptyList_whenStructureParserReturnsNull() {
        when(parsersRegistry.hasSupport("multichoice")).thenReturn(true);
        when(parsersRegistry.get("multichoice")).thenReturn(structureParser);
        when(structureParser.parseMoodleStructure(any())).thenReturn(null);

        InputStream stream = new ByteArrayInputStream(VALID_XML_MULTICHOICE.getBytes(StandardCharsets.UTF_8));

        List<Question> result = parser.parseMoodleQuestions(stream, List.of(1), 1);

        assertTrue(result.isEmpty());
    }

    @Test
    void parseMoodleQuestions_shouldReturnOneQuestion_whenXmlIsValidAndIndexMatches() {
        when(parsersRegistry.hasSupport("multichoice")).thenReturn(true);
        when(parsersRegistry.get("multichoice")).thenReturn(structureParser);
        when(structureParser.parseMoodleStructure(any())).thenReturn("{\"answers\":[]}");

        InputStream stream = new ByteArrayInputStream(VALID_XML_MULTICHOICE.getBytes(StandardCharsets.UTF_8));

        List<Question> result = parser.parseMoodleQuestions(stream, List.of(1), 42);

        assertEquals(1, result.size());
    }

    @Test
    void parseMoodleQuestions_shouldOnlyParseSelectedIndexes() {
        when(parsersRegistry.hasSupport("multichoice")).thenReturn(true);
        when(parsersRegistry.get("multichoice")).thenReturn(structureParser);
        when(structureParser.parseMoodleStructure(any())).thenReturn("{\"answers\":[]}");

        InputStream stream = new ByteArrayInputStream(VALID_XML_TWO_MULTICHOICE.getBytes(StandardCharsets.UTF_8));

        List<Question> result = parser.parseMoodleQuestions(stream, List.of(2), 1);

        assertEquals(1, result.size());
    }

    @Test
    void parseMoodleQuestions_shouldSetQuestionStructure_fromParser() {
        String expectedStructure = "{\"answers\":[{\"id\":1}]}";

        when(parsersRegistry.hasSupport("multichoice")).thenReturn(true);
        when(parsersRegistry.get("multichoice")).thenReturn(structureParser);
        when(structureParser.parseMoodleStructure(any())).thenReturn(expectedStructure);

        InputStream stream = new ByteArrayInputStream(VALID_XML_MULTICHOICE.getBytes(StandardCharsets.UTF_8));

        List<Question> result = parser.parseMoodleQuestions(stream, List.of(1), 1);

        assertEquals(expectedStructure, result.get(0).getQuestionStructure());
    }

    // ==================== parseDomainQuestions ====================

    @Test
    void parseDomainQuestions_shouldReturnXmlWithQuizRootElement() {
        List<Question> questions = List.of();

        String result = new String(parser.parseDomainQuestions(questions), StandardCharsets.UTF_8);

        assertTrue(result.contains("<quiz>"));
        assertTrue(result.contains("</quiz>"));
    }

    @Test
    void parseDomainQuestions_shouldAlwaysIncludeDefaultCategory() {
        List<Question> questions = List.of();

        String result = new String(parser.parseDomainQuestions(questions), StandardCharsets.UTF_8);

        assertTrue(result.contains("type=\"category\""));
        assertTrue(result.contains("$course$/Categoria por defecto"));
    }

    @Test
    void parseDomainQuestions_shouldSkipQuestion_whenTypeIsNotSupported() {
        Question question = new Question();
        question.setQuestionType("UNSUPPORTED_TYPE");

        when(parsersRegistry.hasSupport(null)).thenReturn(false);

        String result = new String(parser.parseDomainQuestions(List.of(question)), StandardCharsets.UTF_8);

        assertFalse(result.contains("<question type=\"UNSUPPORTED_TYPE\">"));
    }

    @Test
    void parseDomainQuestions_shouldSkipQuestion_whenStructureParserReturnsNull() {
        Question question = new Question();
        question.setQuestionType("MULTIPLE_CHOICE");
        question.setQuestionTitle("Título");
        question.setQuestionText("Texto");

        when(parsersRegistry.hasSupport("multichoice")).thenReturn(true);
        when(parsersRegistry.get("multichoice")).thenReturn(structureParser);
        when(structureParser.parseQuestionStructure(any())).thenReturn(null);

        String result = new String(parser.parseDomainQuestions(List.of(question)), StandardCharsets.UTF_8);

        assertFalse(result.contains("type=\"multichoice\""));
    }

    @Test
    void parseDomainQuestions_shouldIncludeQuestion_whenStructureIsValid() {
        Question question = new Question();
        question.setQuestionType("MULTIPLE_CHOICE");
        question.setQuestionTitle("Título de prueba");
        question.setQuestionText("Texto de prueba");

        when(parsersRegistry.hasSupport("multichoice")).thenReturn(true);
        when(parsersRegistry.get("multichoice")).thenReturn(structureParser);
        when(structureParser.parseQuestionStructure(any())).thenReturn("<answer>...</answer>");

        String result = new String(parser.parseDomainQuestions(List.of(question)), StandardCharsets.UTF_8);

        assertTrue(result.contains("type=\"multichoice\""));
        assertTrue(result.contains("Título de prueba"));
        assertTrue(result.contains("Texto de prueba"));
    }

    @Test
    void parseDomainQuestions_shouldUseEmptyString_whenQuestionTitleIsNull() {
        Question question = new Question();
        question.setQuestionType("MULTIPLE_CHOICE");
        question.setQuestionTitle(null);
        question.setQuestionText("Texto");

        when(parsersRegistry.hasSupport("multichoice")).thenReturn(true);
        when(parsersRegistry.get("multichoice")).thenReturn(structureParser);
        when(structureParser.parseQuestionStructure(any())).thenReturn("<answer/>");

        String result = new String(parser.parseDomainQuestions(List.of(question)), StandardCharsets.UTF_8);

        assertTrue(result.contains("<name><text></text></name>"));
    }

    @Test
    void parseDomainQuestions_shouldIncludeDefaultGradeAndPenalty() {
        Question question = new Question();
        question.setQuestionType("MULTIPLE_CHOICE");
        question.setQuestionTitle("Título");
        question.setQuestionText("Texto");

        when(parsersRegistry.hasSupport("multichoice")).thenReturn(true);
        when(parsersRegistry.get("multichoice")).thenReturn(structureParser);
        when(structureParser.parseQuestionStructure(any())).thenReturn("<answer/>");

        String result = new String(parser.parseDomainQuestions(List.of(question)), StandardCharsets.UTF_8);

        assertTrue(result.contains("<defaultgrade>1.0000000</defaultgrade>"));
        assertTrue(result.contains("<penalty>0.3333333</penalty>"));
    }

    @Test
    void parseDomainQuestions_shouldReturnUtf8EncodedBytes() {
        Question question = new Question();
        question.setQuestionType("MULTIPLE_CHOICE");
        question.setQuestionTitle("Título con ñ y acentós");
        question.setQuestionText("Texto");

        when(parsersRegistry.hasSupport("multichoice")).thenReturn(true);
        when(parsersRegistry.get("multichoice")).thenReturn(structureParser);
        when(structureParser.parseQuestionStructure(any())).thenReturn("<answer/>");

        byte[] result = parser.parseDomainQuestions(List.of(question));
        String resultStr = new String(result, StandardCharsets.UTF_8);

        assertTrue(resultStr.contains("ñ"));
    }
}
