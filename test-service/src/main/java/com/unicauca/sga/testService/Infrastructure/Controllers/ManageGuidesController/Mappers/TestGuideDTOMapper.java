package com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.Mappers;

import com.unicauca.sga.testService.Domain.Models.TestGuide;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Request.TestGuideDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageGuidesController.DTOs.Response.TestGuideDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TestGuideDTOMapper {
    @Mapping(target = "testGuideArchive", ignore = true)
    TestGuide toModel(TestGuideDTORequest testGuideDTORequest);
    TestGuideDTOResponse toDTO(TestGuide testGuide);
}
