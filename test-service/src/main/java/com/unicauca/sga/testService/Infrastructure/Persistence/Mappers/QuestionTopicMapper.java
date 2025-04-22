package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Model.QuestionTopic;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTopicTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionTopicMapper {
    QuestionTopic toModel(QuestionTopicTable questionTopicTable);
    QuestionTopicTable toInfra(QuestionTopic questionTopic);
}
