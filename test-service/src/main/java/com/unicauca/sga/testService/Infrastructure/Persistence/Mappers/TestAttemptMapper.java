package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestAttemptTable;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TestMapper.class, AnswerMapper.class})
public interface TestAttemptMapper {
    TestAttempt toModel(TestAttemptTable testAttempt, @Context CycleAvoidingMappingContext context);
    TestAttemptTable toInfra(TestAttempt testAttempt, @Context CycleAvoidingMappingContext context);
}
