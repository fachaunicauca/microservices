package com.unicauca.sga.courseService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.courseService.Domain.Models.StudentEnrollment;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.StudentEnrollmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentEnrollmentMapper.class,
                                            CourseMapper.class,}
)
public interface StudentEnrollmentMapper {
    @Mapping(target = "course", source = "course", qualifiedByName = "courseToModelWithoutStudents")
    StudentEnrollment toModel(StudentEnrollmentEntity studentEnrollmentEntity);

    StudentEnrollmentEntity toInfra(StudentEnrollment studentEnrollment);
}
