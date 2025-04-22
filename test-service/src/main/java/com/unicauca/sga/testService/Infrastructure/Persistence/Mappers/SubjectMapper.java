package com.unicauca.sga.testService.Infrastructure.Persistence.Mappers;

import com.unicauca.sga.testService.Domain.Model.Subject;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.SubjectTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject toModel(SubjectTable subjectTable);
    SubjectTable toInfra(Subject subject);
}
