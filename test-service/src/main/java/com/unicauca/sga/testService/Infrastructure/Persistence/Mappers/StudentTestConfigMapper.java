package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigTable;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TestMapper.class})
public interface StudentTestConfigMapper {
    StudentTestConfig toModel(StudentTestConfigTable studentTestConfig, @Context CycleAvoidingMappingContext context);
    StudentTestConfigTable toInfra(StudentTestConfig studentTestConfig, @Context CycleAvoidingMappingContext context);
}
