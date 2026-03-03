package com.unicauca.sga.testService.Aplication.Services;


import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureGrader;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionStructureGraderRegistry {

    private final Map<String, QuestionStructureGrader> questionStructureHandlerMap = new HashMap<>();

    public QuestionStructureGraderRegistry(List<QuestionStructureGrader> questionStrategies) {
        for(QuestionStructureGrader questionStructureGrader : questionStrategies){
            questionStructureHandlerMap.put(questionStructureGrader.getSupportedType(), questionStructureGrader);
        }
    }

    public QuestionStructureGrader get(String questionType){
        QuestionStructureGrader questionStructureGrader = questionStructureHandlerMap.get(questionType);

        if(questionStructureGrader == null){
            throw new NotFoundException("No se encontró el algoritmo de validación para el tipo de pregunta: " + questionType);
        }

        return questionStructureGrader;
    }
}
