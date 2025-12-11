package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.Answer;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.AnswerTypesMappers.ChoiceAnswerMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.AnswerTypesMappers.OpenEndedAnswerMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTable;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        ChoiceAnswerMapper.class,
        OpenEndedAnswerMapper.class,
        QuestionMapper.class
})
public interface AnswerMapper {
    Answer toModel(AnswerTable answerTable, @Context CycleAvoidingMappingContext context);
    AnswerTable toInfra(Answer answer, @Context CycleAvoidingMappingContext context);
}
