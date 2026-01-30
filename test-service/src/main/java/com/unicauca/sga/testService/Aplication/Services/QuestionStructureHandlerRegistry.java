package com.unicauca.sga.testService.Aplication.Services;


import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionStructureHandlerRegistry {

    private final Map<String, QuestionStructureHandler> questionStructureHandlerMap = new HashMap<>();

    public QuestionStructureHandlerRegistry(List<QuestionStructureHandler> questionStrategies) {
        for(QuestionStructureHandler questionStructureHandler : questionStrategies){
            questionStructureHandlerMap.put(questionStructureHandler.getSupportedType(), questionStructureHandler);
        }
    }

    public QuestionStructureHandler get(String questionType){
        QuestionStructureHandler questionStructureHandler = questionStructureHandlerMap.get(questionType);

        if(questionStructureHandler == null){
            throw new NotFoundException("No se encontró el algoritmo de validación para el tipo de pregunta: " + questionType);
        }

        return questionStructureHandler;
    }
}
