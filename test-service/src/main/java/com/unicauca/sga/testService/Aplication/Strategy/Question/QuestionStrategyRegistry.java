package com.unicauca.sga.testService.Aplication.Strategy.Question;


import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Models.Question.QuestionStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionStrategyRegistry {

    private final Map<String, QuestionStrategy> questionBehaviorsMap = new HashMap<>();

    public QuestionStrategyRegistry(List<QuestionStrategy> questionStrategies) {
        for(QuestionStrategy questionStrategy : questionStrategies){
            questionBehaviorsMap.put(questionStrategy.getSupportedType(), questionStrategy);
        }
    }

    public QuestionStrategy get(String questionType){
        QuestionStrategy questionStrategy = questionBehaviorsMap.get(questionType);

        if(questionStrategy == null){
            throw new NotFoundException("No se encontró el algoritmo de validación para el tipo " + questionType);
        }

        return questionStrategy;
    }
}
