package com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.Request.TestDTORequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestDTORequestMapper {
    TestDTORequest toDTO(Test test);
    Test toModel(TestDTORequest testDTORequest );
}
