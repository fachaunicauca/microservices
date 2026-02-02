package com.unicauca.sga.testService.Infrastructure.Controllers.ManageStudentTestConfigController.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageStudentTestConfigController.DTOs.Response.StudentTestConfigDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentTestConfigDTOMapper {
    StudentTestConfigDTOResponse toDTO(StudentTestConfig studentTestConfig);
}
