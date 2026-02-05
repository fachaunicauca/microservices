package com.unicauca.sga.courseService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.courseService.Domain.Models.Student;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.StudentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toModel(StudentEntity entity);
    StudentEntity toInfra(Student model);
}
