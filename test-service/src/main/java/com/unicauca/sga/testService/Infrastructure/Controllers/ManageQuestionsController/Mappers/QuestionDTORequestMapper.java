package com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageQuestionsController.DTOs.Request.QuestionDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.Mappers.TestDTORequestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TestDTORequestMapper.class})
public interface QuestionDTORequestMapper {
    QuestionDTORequest toDTO(Question question);
    Question toModel(QuestionDTORequest questionDTO);
}
