package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class})
public interface TestMapper {
    Test toModel(TestEntity testEntity, @Context CycleAvoidingMappingContext context);
    TestEntity toInfra(Test test, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "questions", ignore = true)
    void update(Test test, @MappingTarget TestEntity testEntity);
}
