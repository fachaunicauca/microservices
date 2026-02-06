package com.unicauca.sga.courseService.Infrastructure.Controllers.ManageStudentsController.Mappers;

import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageStudentsController.DTOs.Request.StudentDTORequest;
import com.unicauca.sga.courseService.Infrastructure.Controllers.ManageStudentsController.DTOs.Response.StudentDTOResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentDTOMapper {
    Student toModel(StudentDTORequest studentDTORequest);
    StudentDTOResponse toDTO(Student student);
}
