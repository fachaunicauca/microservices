package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.Test;
import com.unicauca.sga.testService.Infrastructure.Context.CycleAvoidingMappingContext;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {QuestionMapper.class})
public interface TestMapper {
    @Mapping(target = "questions", ignore = true)
    Test toModel(TestEntity testEntity);
    @Mapping(target = "questions", ignore = true)
    TestEntity toInfra(Test test);

    @Mapping(target = "questions", ignore = true)
    void updateInfra(Test test, @MappingTarget TestEntity testEntity);
}
