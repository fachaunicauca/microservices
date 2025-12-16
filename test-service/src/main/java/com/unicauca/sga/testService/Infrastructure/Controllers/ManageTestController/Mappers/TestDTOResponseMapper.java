package com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.Response.TestDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestDTOResponseMapper {
    TestDTOResponse toDTO(Test test);
    Test toModel(TestDTOResponse dto);
}
