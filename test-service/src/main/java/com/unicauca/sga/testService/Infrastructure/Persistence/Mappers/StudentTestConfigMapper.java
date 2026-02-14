package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentTestConfig;
import com.unicauca.sga.testService.Domain.Models.StudentTestResult;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.StudentTestConfigEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TestMapper.class})
public interface StudentTestConfigMapper {
    StudentTestConfig toModel(StudentTestConfigEntity studentTestConfig);

    StudentTestConfigEntity toInfra(StudentTestConfig studentTestConfig);

    @Mapping(target = "test", ignore = true)
    StudentTestConfig toModelWithoutTest(StudentTestConfigEntity studentTestConfig);

    StudentTestResult toStudentResultModel(StudentTestConfigEntity studentTestConfig);
}
