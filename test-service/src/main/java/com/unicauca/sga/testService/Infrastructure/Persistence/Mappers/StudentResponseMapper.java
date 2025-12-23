package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentResponse;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentResponseEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentResponseMapper {
    StudentResponse toModel(StudentResponseEntity studentResponse, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
    StudentResponseEntity toInfra(StudentResponse studentResponse, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
