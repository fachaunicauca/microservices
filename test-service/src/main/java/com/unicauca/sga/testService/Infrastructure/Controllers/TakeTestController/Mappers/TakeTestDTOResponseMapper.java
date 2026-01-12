package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.TakeTestDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.TakeTestQuestionDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TakeTestDTOResponseMapper {
    TakeTestDTOResponse toDTO(Test test);
    TakeTestQuestionDTOResponse QuestiontoDTO(Question question);
}
