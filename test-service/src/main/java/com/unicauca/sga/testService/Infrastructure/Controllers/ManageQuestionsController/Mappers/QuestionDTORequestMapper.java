package com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.DTOs.Request.QuestionDTORequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuestionDTORequestMapper {
    @Mapping(source = "testId", target = "test.testId")
    Question toModel(QuestionDTORequest questionDTO);
}
