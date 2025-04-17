package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Model.Answer;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses={QuestionMapper.class})
public interface AnswerMapper {
    @Mapping(target = "question", ignore = true)
    Answer toModel(AnswerTable answerTable);
    @Mapping(target = "question", ignore = true)
    AnswerTable toInfra(Answer answer);
}
