package com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.Mappers;

import com.unicauca.sga.testService.Domain.Models.StudentResponse.StudentResponse;
import com.unicauca.sga.testService.Domain.Models.TestAttempt;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Request.StudentResponseDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Request.StudentTestAttemptDTORequest;
import com.unicauca.sga.testService.Infrastructure.Controllers.TakeTestController.DTOs.Response.StudentTestAttemptDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentTestAttemptDTOMapper {
    @Mapping(source = "testId", target = "test.testId")
    TestAttempt toModel(StudentTestAttemptDTORequest studentTestAttemptDTORequest);

    StudentTestAttemptDTOResponse toDTO(TestAttempt testAttempt);

    StudentResponse StudentResponseDTOToModel(StudentResponseDTORequest studentResponseDTORequest);
}
