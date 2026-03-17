package com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionStructureParsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;
import org.springframework.stereotype.Component;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

@Component
public class MoodleQSMultipleChoiceParser implements MoodleQStructureParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<String> getSupportedMoodleTypes() {
        return List.of("multichoice", "truefalse");
    }

    @Override
    public String parseMoodleStructure(Element questionEl) {
        // Obtener todas las etiquetas answer
        NodeList answerNodes = questionEl.getElementsByTagName("answer");

        MultipleChoiceStructure structure = new MultipleChoiceStructure();
        List<ChoiceAnswer> choiceAnswers = new ArrayList<>();
        int correctAnswerCount = 0;

        // Parsear cada respuesta
        for(int i = 0; i < answerNodes.getLength(); i++){
            Element answerEl = (Element) answerNodes.item(i);
            ChoiceAnswer answer = new ChoiceAnswer();

            answer.setId((long) i + 1);
            answer.setText(getTextContent(answerEl, "text"));

            if(answerEl.getAttribute("fraction").equals("100")){
                answer.setCorrect(true);
                correctAnswerCount++;
            }else{
                answer.setCorrect(false);
            }

            choiceAnswers.add(answer);
        }

        structure.setAnswers(choiceAnswers);
        structure.setCorrectAnswerCount(correctAnswerCount);

        try {
            return objectMapper.writeValueAsString(structure);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    // Helper para extraer el texto de un tag hijo
    private String getTextContent(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        if (nodes.getLength() == 0) return "";

        String raw = nodes.item(0).getTextContent();

        // Limpiar etiquetas HTML que Moodle puede incluir en el texto
        return raw.replaceAll("<[^>]+>", "").trim();
    }
}
