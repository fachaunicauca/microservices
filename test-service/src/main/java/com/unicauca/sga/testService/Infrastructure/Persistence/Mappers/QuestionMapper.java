package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.Question;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionTypesMappers.MultipleChoiceQuestionMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionTypesMappers.OpenEndedQuestionMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.QuestionTypesMappers.SingleChoiceQuestionMapper;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        SingleChoiceQuestionMapper.class,
        MultipleChoiceQuestionMapper.class,
        OpenEndedQuestionMapper.class,
        TestMapper.class,
})
public interface QuestionMapper {
    Question toModel(QuestionTable question, @Context CycleAvoidingMappingContext context);
    QuestionTable toInfra(Question question, @Context CycleAvoidingMappingContext context);
}
