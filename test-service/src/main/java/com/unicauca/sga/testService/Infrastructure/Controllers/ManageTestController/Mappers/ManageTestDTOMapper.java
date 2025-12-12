package com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.Mappers;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageTestController.DTOs.ManageTestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManageTestDTOMapper {
    ManageTestDTO toDTO(Test test);
    Test toModel(ManageTestDTO dto);
}
