package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.Question;
import com.unicauca.sga.testService.Domain.Models.QuestionTypes.MultipleChoiceQuestion;
import com.unicauca.sga.testService.Domain.Models.QuestionTypes.OpenEndedQuestion;
import com.unicauca.sga.testService.Domain.Models.QuestionTypes.SingleChoiceQuestion;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTypes.MultipleChoiceQuestionTable;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTypes.OpenEndedQuestionTable;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTypes.SingleChoiceQuestionTable;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    MultipleChoiceQuestion MCQuestionToModel(MultipleChoiceQuestionTable question, @Context CycleAvoidingMappingContext context);
    MultipleChoiceQuestionTable MCQuestionToInfra(MultipleChoiceQuestion question, @Context CycleAvoidingMappingContext context);

    SingleChoiceQuestion SCQuestionToModel(SingleChoiceQuestionTable question, @Context CycleAvoidingMappingContext context);
    SingleChoiceQuestionTable SCQuestionToInfra(SingleChoiceQuestion question, @Context CycleAvoidingMappingContext context);

    OpenEndedQuestion OPQuestionToModel(OpenEndedQuestionTable question, @Context CycleAvoidingMappingContext context);
    OpenEndedQuestionTable OPQuestionToInfra(OpenEndedQuestion question, @Context CycleAvoidingMappingContext context);

    default Question toModel(QuestionTable question, @Context CycleAvoidingMappingContext context) {
        if(question instanceof MultipleChoiceQuestionTable){
            return MCQuestionToModel((MultipleChoiceQuestionTable) question, context);
        }
        else if(question instanceof SingleChoiceQuestionTable){
            return SCQuestionToModel((SingleChoiceQuestionTable) question, context);
        }
        else if(question instanceof OpenEndedQuestionTable){
            return OPQuestionToModel((OpenEndedQuestionTable) question, context);
        }
        else{
            return null;
        }
    }

    default QuestionTable toInfra(Question question, @Context CycleAvoidingMappingContext context) {
        if(question instanceof MultipleChoiceQuestion){
            return MCQuestionToInfra((MultipleChoiceQuestion) question, context);
        }
        else if(question instanceof SingleChoiceQuestion){
            return SCQuestionToInfra((SingleChoiceQuestion) question, context);
        }
        else if(question instanceof OpenEndedQuestion){
            return OPQuestionToInfra((OpenEndedQuestion) question, context);
        }
        else {
            return null;
        }
    }
}
