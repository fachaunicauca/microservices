package com.unicauca.sga.courseService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.courseService.Domain.Models.StudentEnrollment;
import com.unicauca.sga.courseService.Infrastructure.Persistence.Tables.StudentEnrollmentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {StudentMapper.class,
                                            CourseMapper.class,}
)
public interface StudentEnrollmentMapper {
    StudentEnrollment toModel(StudentEnrollmentEntity studentEnrollmentEntity);
    StudentEnrollmentEntity toInfra(StudentEnrollment studentEnrollment);
}
