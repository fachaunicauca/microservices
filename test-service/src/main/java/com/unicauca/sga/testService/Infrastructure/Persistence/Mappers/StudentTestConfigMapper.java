package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TestMapper.class})
public interface StudentTestConfigMapper {
    @Mapping(target = "test", ignore = true)
    StudentTestConfig toModel(StudentTestConfigEntity studentTestConfig);

    StudentTestConfigEntity toInfra(StudentTestConfig studentTestConfig);
}
