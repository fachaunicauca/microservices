package com.unicauca.sga.testService.Infrastructure.Services;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Domain.Services.IMoodleQuestionParser;
import com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionStructureParsers.MoodleQStructureParser;
import com.unicauca.sga.testService.Infrastructure.Utils.XMLUtils;
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MoodleQuestionParser implements IMoodleQuestionParser {

    private final Map<String, String> moodleToDomainTypeMap = Map.of(
            "multichoice", "MULTIPLE_CHOICE",
            "truefalse", "MULTIPLE_CHOICE"
    );

    private static final Map<String, String> domainToMoodleTypeMap  = Map.of(
            "MULTIPLE_CHOICE", "multichoice"
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

                question.setQuestionTitle(XMLUtils.getTextContent(questionEl, "name"));
                question.setQuestionText(XMLUtils.getTextContent(questionEl, "questiontext"));
                question.setQuestionType(moodleToDomainTypeMap.get(type));
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

    @Override
    public byte[] parseDomainQuestions(List<Question> questionList) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<quiz>\n");

        // Agregar categoria por defecto
        xml.append("\t<question type=\"category\">\n")
                .append("\t\t<category><text>$course$/Categoria por defecto</text></category>\n")
                .append("\t</question>\n\n");

        for (Question q : questionList) {
            String moodleType = domainToMoodleTypeMap.get(q.getQuestionType());

            // Parsear estructura de pregunta
            if (!parsersRegistry.hasSupport(moodleType)) continue;
            MoodleQStructureParser parser = parsersRegistry.get(moodleType);
            String xmlStructure = parser.parseQuestionStructure(q);

            // Si no se pudo parsear la estructura = no agregar la pregunta
            if (xmlStructure == null) continue;

            // Tipo de pregunta
            xml.append("\t<question type=\"").append(moodleType).append("\">\n");
            // Titulo
            xml.append("\t\t<name><text>").append(q.getQuestionTitle() != null ? XMLUtils.escapeXml(q.getQuestionTitle()) : "")
                    .append("</text></name>\n");
            // Texto
            xml.append("\t<questiontext format=\"html\">\n")
                    .append("\t\t<text><![CDATA[").append(q.getQuestionText()).append("]]></text>\n")
                    .append("\t</questiontext>\n");
            // Configuraciones por defecto
            xml.append("\t<defaultgrade>1.0000000</defaultgrade>\n")
                    .append("\t<penalty>0.3333333</penalty>\n");
            // Estructura de pregunta
            xml.append(xmlStructure);

            xml.append("\t</question>\n\n");
        }

        xml.append("</quiz>");
        return xml.toString().getBytes(StandardCharsets.UTF_8);
    }
}
