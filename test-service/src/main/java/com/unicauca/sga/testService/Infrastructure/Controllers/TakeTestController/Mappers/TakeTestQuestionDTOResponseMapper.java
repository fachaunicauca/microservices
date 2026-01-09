package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.TakeTestQuestionDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TakeTestQuestionDTOResponseMapper {
    TakeTestQuestionDTOResponse toDTO(Question question);
}
