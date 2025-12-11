package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionTypesMappers;

import com.unicauca.sga.testService.Domain.Models.QuestionTypes.MultipleChoiceQuestion;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.AnswerMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTypes.MultipleChoiceQuestionTable;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AnswerMapper.class})
public interface MultipleChoiceQuestionMapper {
    MultipleChoiceQuestion toModel(MultipleChoiceQuestionTable multipleChoiceQuestionTable, @Context CycleAvoidingMappingContext context);
    MultipleChoiceQuestionTable toInfra(MultipleChoiceQuestion multipleChoiceQuestion, @Context CycleAvoidingMappingContext context);
}
