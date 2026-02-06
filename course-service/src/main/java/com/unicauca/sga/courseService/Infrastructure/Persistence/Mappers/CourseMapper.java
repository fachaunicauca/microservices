package com.unicauca.sga.courseService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.courseService.Domain.Models.Course;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface CourseMapper {
    Course toModelWithoutStudents(CourseEntity entity);

    CourseEntity toInfra(Course model);
}
