package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.TakeTestDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TakeTestQuestionDTOResponseMapper.class})
public interface TakeTestDTOResponseMapper {
    TakeTestDTOResponse toDTO(Test test);
}
