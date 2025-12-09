package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.OldVersion.Test;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestMapper {
    Test toModel(TestTable testTable);
    TestTable toInfra(Test test);
}
