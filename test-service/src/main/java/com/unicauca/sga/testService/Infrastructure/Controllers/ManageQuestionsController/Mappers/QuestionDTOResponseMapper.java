package com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.DTOs.Response.QuestionDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionDTOResponseMapper {
    QuestionDTOResponse toDTO(Question question);
    Question toModel(QuestionDTOResponse questionDTO);
}
