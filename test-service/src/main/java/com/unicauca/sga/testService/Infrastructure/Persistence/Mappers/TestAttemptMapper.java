package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.TestAttemptEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {TestMapper.class, StudentResponseMapper.class})
public interface TestAttemptMapper {
    @Mapping(target = "studentResponses", ignore = true)
    TestAttempt toModel(TestAttemptEntity testAttempt);

    TestAttemptEntity toInfra(TestAttempt testAttempt);

    @AfterMapping
    default void linkStudentResponses(@MappingTarget TestAttemptEntity entity) {
        if (entity.getStudentResponses() != null) {
            entity.getStudentResponses().forEach(response -> response.setTestAttempt(entity));
        }
    }
}
