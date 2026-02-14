package com.unicauca.sga.testService.Infrastructure.Clients.StudentServiceClient.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentTestResult;
import com.unicauca.sga.testService.Infrastructure.Clients.StudentServiceClient.DTOs.StudentDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentDTOMapper {
    StudentTestResult toModel(StudentDTOResponse studentDTOResponse);
}
