package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {TestMapper.class})
public interface QuestionMapper {
    Question toModel(QuestionEntity questionEntity, @Context CycleAvoidingMappingContext context);
    QuestionEntity toInfra(Question question, @Context CycleAvoidingMappingContext context);
}
