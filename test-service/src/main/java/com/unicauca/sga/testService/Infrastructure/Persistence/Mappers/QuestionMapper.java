package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "test.questions", ignore = true)
    Question toModel(QuestionEntity questionEntity);
    @Mapping(target = "test.questions", ignore = true)
    QuestionEntity toInfra(Question question);
    @Mapping(target = "test", ignore = true)
    Question toModelWithoutTest(QuestionEntity questionEntity);
}
