package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestAttemptEntity;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TestMapper.class, StudentResponseMapper.class})
public interface TestAttemptMapper {
    TestAttempt toModel(TestAttemptEntity testAttempt, @Context CycleAvoidingMappingContext context);
    TestAttemptEntity toInfra(TestAttempt testAttempt, @Context CycleAvoidingMappingContext context);
}
