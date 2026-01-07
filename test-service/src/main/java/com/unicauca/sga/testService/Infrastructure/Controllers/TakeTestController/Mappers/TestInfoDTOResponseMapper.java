package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.TestInfoDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestInfoDTOResponseMapper {
    TestInfoDTOResponse toDTO(Test test);
}
