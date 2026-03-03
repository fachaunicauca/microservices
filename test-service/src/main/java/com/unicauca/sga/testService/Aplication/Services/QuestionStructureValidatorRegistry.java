package com.unicauca.sga.testService.Aplication.Services;

import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Services.QuestionStructureValidator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QuestionStructureValidatorRegistry {

    private final Map<String, QuestionStructureValidator> questionStructureValidatorMap = new HashMap<>();

    public QuestionStructureValidatorRegistry(List<QuestionStructureValidator> questionStructureValidators) {
        for(QuestionStructureValidator questionStructureValidator : questionStructureValidators){
            questionStructureValidatorMap.put(questionStructureValidator.getSupportedType(), questionStructureValidator);
        }
    }

    public QuestionStructureValidator get(String type) {
        QuestionStructureValidator validator = questionStructureValidatorMap.get(type);

        if(validator == null){
            throw new NotFoundException("No se encontró el algoritmo de validación para el tipo de pregunta: " + type);
        }

        return validator;
    }

}
