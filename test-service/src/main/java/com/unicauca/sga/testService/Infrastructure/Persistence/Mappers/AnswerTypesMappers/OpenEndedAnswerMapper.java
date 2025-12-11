package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers.AnswerTypesMappers;

import com.unicauca.sga.testService.Domain.Models.AnswerTypes.OpenEndedAnswer;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTypes.OpenEndedAnswerTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpenEndedAnswerMapper {
    OpenEndedAnswer toModel(OpenEndedAnswerTable answerTable);
    OpenEndedAnswerTable toInfra(OpenEndedAnswer answerTable);
}
