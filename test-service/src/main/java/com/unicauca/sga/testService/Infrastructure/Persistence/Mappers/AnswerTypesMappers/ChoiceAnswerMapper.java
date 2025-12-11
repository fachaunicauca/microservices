package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.AnswerTypesMappers;

import com.unicauca.sga.testService.Domain.Models.AnswerTypes.ChoiceAnswer;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTypes.ChoiceAnswerTable;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChoiceAnswerMapper {
    ChoiceAnswer toModel(ChoiceAnswerTable answerTable, @Context CycleAvoidingMappingContext context);
    ChoiceAnswerTable toInfra(ChoiceAnswer answer, @Context CycleAvoidingMappingContext context);
}
