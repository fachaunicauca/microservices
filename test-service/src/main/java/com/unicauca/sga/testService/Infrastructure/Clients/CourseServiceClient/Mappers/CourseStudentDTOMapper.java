package com.unicauca.sga.testService.Infrastructure.Clients.CourseServiceClient.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentTestResult;
import com.unicauca.sga.testService.Infrastructure.Clients.CourseServiceClient.DTOs.CourseStudentDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseStudentDTOMapper {
    StudentTestResult toModel(CourseStudentDTOResponse courseStudentDTOResponse);
}
