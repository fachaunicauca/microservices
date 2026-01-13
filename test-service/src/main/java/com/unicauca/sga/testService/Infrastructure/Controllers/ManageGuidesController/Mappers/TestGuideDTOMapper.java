package com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.Mappers;

import com.unicauca.sga.testService.Domain.Models.TestGuide;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Request.TestGuideDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Response.TestGuideDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestGuideDTOMapper {
    TestGuide toModel(TestGuideDTORequest testGuideDTORequest);
    TestGuideDTOResponse toDTO(TestGuide testGuide);
}
