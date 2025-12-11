package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionTypesMappers;

import com.unicauca.sga.testService.Domain.Models.QuestionTypes.SingleChoiceQuestion;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.AnswerMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTypes.SingleChoiceQuestionTable;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class})
public interface SingleChoiceQuestionMapper {
    SingleChoiceQuestion toModel(SingleChoiceQuestionTable question, @Context CycleAvoidingMappingContext context);
    SingleChoiceQuestionTable toInfra(SingleChoiceQuestion question, @Context CycleAvoidingMappingContext context);
}
