package com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.Mappers;

import com.unicauca.sga.testService.Domain.Models.TestResults.StudentTestResult;
import com.unicauca.sga.testService.Domain.Models.TestResults.TestStats;
import com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.DTOs.StudentsResultsDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Controllers.GenerateTestResultsController.DTOs.TestStatsDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestResultsDTOMapper {
    StudentsResultsDTOResponse studentResultstoDTO(StudentTestResult studentTestResult);
    TestStatsDTOResponse testStatsToDTO(TestStats testStats);
}
