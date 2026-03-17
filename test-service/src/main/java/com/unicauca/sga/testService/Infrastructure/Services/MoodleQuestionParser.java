package com.unicauca.sga.testService.Infrastructure.Services;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Services.IMoodleQuestionParser;
import com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionStructureParsers.MoodleQStructureParser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import org.springframework.web.server.ResponseStatusException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MoodleQuestionParser implements IMoodleQuestionParser {

    private final Map<String, String> typesTranslator = Map.of(
            "multichoice", "MULTIPLE_CHOICE",
            "truefalse", "MULTIPLE_CHOICE"
    );
    private final MoodleQStructureParserRegistry parsersRegistry;

    public List<Question> parseMoodleQuestions(InputStream xmlStream, List<Integer> selectedIndexes, int testId) {
        List<Question> questions = new ArrayList<>();

        Test test = new Test();
        test.setTestId(testId);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlStream);
            doc.getDocumentElement().normalize();

            NodeList questionNodes = doc.getElementsByTagName("question");

            int index = 1;

            for (int i = 0; i < questionNodes.getLength(); i++) {
                // Solo procesar los índices seleccionados
                if (!selectedIndexes.contains(index)) {
                    index++;
                    continue;
                }

                Node node = questionNodes.item(i);
                if (node.getNodeType() != Node.ELEMENT_NODE) continue;

                Element questionEl = (Element) node;
                String type = questionEl.getAttribute("type");

                // Saltar categorías y tipos no soportados
                if (!parsersRegistry.hasSupport(type)) continue;

                // Crear pregunta
                System.out.println("Procesando Pregunta " + index);
                Question question = new Question();

                question.setQuestionTitle(getTextContent(questionEl, "name"));
                question.setQuestionText(getTextContent(questionEl, "questiontext"));
                question.setQuestionType(typesTranslator.get(type));
                question.setTest(test);

                // Parsear estructura de pregunta
                MoodleQStructureParser parser = parsersRegistry.get(type);
                String structure = parser.parseMoodleStructure(questionEl);

                // Si el parseo falla devuelve null
                if(structure == null) continue;

                question.setQuestionStructure(structure);

                questions.add(question);
                index++;
            }

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocurrió un erro al procesar el archivo importado");
        }

        return questions;
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
