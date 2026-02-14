package com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentTestResult;
import com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.DTOs.StudentsResultsDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentsResultsDTOMapper {
    StudentsResultsDTOResponse toDTO(StudentTestResult studentTestResult);
}
