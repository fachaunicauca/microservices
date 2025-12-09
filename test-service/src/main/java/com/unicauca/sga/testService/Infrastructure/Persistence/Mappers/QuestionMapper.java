package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.OldVersion.Question;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {AnswerMapper.class})
public interface QuestionMapper {
    Question toModel(QuestionTable questionTable);
    QuestionTable toInfra (Question question);
}
