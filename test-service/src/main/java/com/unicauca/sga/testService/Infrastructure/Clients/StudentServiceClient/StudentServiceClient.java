package com.unicauca.sga.testService.Infrastructure.Clients.StudentServiceClient;

import com.unicauca.sga.testService.Domain.Models.StudentTestResult;
import com.unicauca.sga.testService.Domain.Services.IStudentService;
import com.unicauca.sga.testService.Infrastructure.Clients.StudentServiceClient.DTOs.StudentDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Clients.StudentServiceClient.Mappers.StudentDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentServiceClient implements IStudentService {

    private final RestClient restClient;
    private final StudentDTOMapper studentDTOMapper;

    @Override
    public List<StudentTestResult> getStudentsByEmails(Collection<String> emails) {
        List<StudentDTOResponse> students = restClient.post()
            .uri("http://course-service/students/byEmails")
            .body(emails)
            .retrieve()
            .body(new ParameterizedTypeReference<>() {});

        if(students == null || students.isEmpty()){
            return List.of();
        }

        return students.stream().map(studentDTOMapper::toModel).toList();
    }
}
