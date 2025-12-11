package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestTable;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class})
public interface TestMapper {
    Test toModel(TestTable testTable, @Context CycleAvoidingMappingContext context);
    TestTable toInfra(Test test, @Context CycleAvoidingMappingContext context);
}
