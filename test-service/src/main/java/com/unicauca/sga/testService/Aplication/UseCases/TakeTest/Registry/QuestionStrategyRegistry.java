package com.unicauca.sga.testService.Aplication.UseCases.TakeTest.Registry;


import com.unicauca.sga.testService.Aplication.UseCases.TakeTest.Strategy.QuestionStrategy;
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
        return questionBehaviorsMap.get(questionType);
    }
}
