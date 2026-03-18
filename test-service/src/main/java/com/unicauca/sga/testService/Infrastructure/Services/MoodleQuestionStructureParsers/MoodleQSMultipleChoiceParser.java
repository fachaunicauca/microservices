package com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionStructureParsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStructures.MultipleChoiceStructure;
import com.unicauca.sga.testService.Infrastructure.Utils.XMLUtils;
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
            answer.setText(XMLUtils.getTextContent(answerEl, "text"));

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

    @Override
    public String parseQuestionStructure(Question question) {
        try {
            MultipleChoiceStructure structure = objectMapper.readValue(question.getQuestionStructure(), MultipleChoiceStructure.class);
            StringBuilder xmlStructure = new StringBuilder();

            // Configuraciones propias del tipo
            xmlStructure.append("\t<shuffleanswers>true</shuffleanswers>\n")
                    .append("\t<answernumbering>abc</answernumbering>\n");
            int correctCount = structure.getCorrectAnswerCount();
            if (correctCount > 1) {
                xmlStructure.append("\t<single>false</single>\n");
            } else {
                xmlStructure.append("\t<single>true</single>\n");
            }

            // Fracción por respuesta correcta (deben sumar 100)
            double correctFraction = correctCount > 0 ? 100.0 / correctCount : 0;

            // Parsear respuestas
            for (ChoiceAnswer c : structure.getAnswers()) {
                // Correcta
                xmlStructure.append("\t<answer fraction=\"").append(c.getCorrect() ? correctFraction : 0).append("\">\n");
                // Texto
                xmlStructure.append("\t\t<text>").append(XMLUtils.escapeXml(c.getText())).append("</text>\n");

                xmlStructure.append("\t</answer>\n");
            }

            return xmlStructure.toString();
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
