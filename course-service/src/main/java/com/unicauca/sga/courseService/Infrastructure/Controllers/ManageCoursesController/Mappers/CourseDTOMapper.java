package com.unicauca.sga.courseService.Infrastructure.Controllers.ManageCoursesController.Mappers;

import com.unicauca.sga.courseService.Domain.Models.Course;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageCoursesController.DTOs.Request.CourseDTORequest;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageCoursesController.DTOs.Response.CourseDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseDTOMapper {
    Course toModel(CourseDTORequest courseDTORequest);
    CourseDTOResponse toDTO(Course course);
}
