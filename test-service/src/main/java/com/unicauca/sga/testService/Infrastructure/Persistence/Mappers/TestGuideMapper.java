package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Model.TestGuide;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestGuideTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TestGuideMapper {
    TestGuide toModel(TestGuideTable testGuideTable);
    TestGuideTable toInfra(TestGuide testGuide);
}
