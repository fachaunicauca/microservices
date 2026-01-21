package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentResponseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentResponseMapper {
    @Mapping(target = "testAttempt", ignore = true)
    StudentResponse toModel(StudentResponseEntity studentResponse);
    @Mapping(target = "testAttempt", ignore = true)
    StudentResponseEntity toInfra(StudentResponse studentResponse);
}
