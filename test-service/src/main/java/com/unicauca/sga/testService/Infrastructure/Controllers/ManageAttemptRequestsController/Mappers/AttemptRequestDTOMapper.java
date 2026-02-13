package com.unicauca.sga.testService.Infrastructure.Controllers.ManageAttemptRequestsController.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Infrastructure.Controllers.ManageAttemptRequestsController.DTOs.Response.AttemptRequestDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttemptRequestDTOMapper {
    AttemptRequestDTOResponse toDTO(StudentTestConfig studentTestConfig);
}
