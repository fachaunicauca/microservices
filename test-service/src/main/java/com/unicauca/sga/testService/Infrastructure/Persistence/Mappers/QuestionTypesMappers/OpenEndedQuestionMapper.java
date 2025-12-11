package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionTypesMappers;

import com.unicauca.sga.testService.Domain.Models.QuestionTypes.OpenEndedQuestion;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.AnswerMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTypes.OpenEndedQuestionTable;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class})
public interface OpenEndedQuestionMapper {
    OpenEndedQuestion toModel(OpenEndedQuestionTable openEndedQuestionTable, @Context CycleAvoidingMappingContext context);
    OpenEndedQuestionTable toInfra(OpenEndedQuestion openEndedQuestion, @Context CycleAvoidingMappingContext context);
}
