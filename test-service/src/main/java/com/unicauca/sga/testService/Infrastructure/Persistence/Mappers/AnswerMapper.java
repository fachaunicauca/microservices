package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.Answer;
import com.unicauca.sga.testService.Domain.Models.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Domain.Models.AnswerTypes.OpenEndedAnswer;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTable;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTypes.ChoiceAnswerTable;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTypes.OpenEndedAnswerTable;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {
        QuestionMapper.class
})
public interface AnswerMapper {
    ChoiceAnswer ChoiceAnswerToModel(ChoiceAnswerTable answer, @Context CycleAvoidingMappingContext context);
    ChoiceAnswerTable ChoiceAnswerToInfra(ChoiceAnswer answer, @Context CycleAvoidingMappingContext context);

    OpenEndedAnswer OpenEndedAnswerToModel(OpenEndedAnswerTable answer, @Context CycleAvoidingMappingContext context);
    OpenEndedAnswerTable OpenEndedAnswerToInfra(OpenEndedAnswer answer, @Context CycleAvoidingMappingContext context);

    default Answer toModel(AnswerTable answer, @Context CycleAvoidingMappingContext context) {
        if(answer instanceof ChoiceAnswerTable){
            return ChoiceAnswerToModel((ChoiceAnswerTable) answer, context);
        }
        else if(answer instanceof OpenEndedAnswerTable){
            return OpenEndedAnswerToModel((OpenEndedAnswerTable) answer, context);
        }
        else{
            return null;
        }
    }

    default AnswerTable toInfra(Answer answer, @Context CycleAvoidingMappingContext context) {
        if(answer instanceof ChoiceAnswer){
            return ChoiceAnswerToInfra((ChoiceAnswer) answer, context);
        }else if(answer instanceof OpenEndedAnswer){
            return OpenEndedAnswerToInfra((OpenEndedAnswer) answer, context);
        }else{
            return null;
        }
    }
}
