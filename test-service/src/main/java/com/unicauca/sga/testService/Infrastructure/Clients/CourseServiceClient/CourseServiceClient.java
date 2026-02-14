package com.unicauca.sga.testService.Infrastructure.Clients.CourseServiceClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicauca.sga.testService.Domain.Models.StudentTestResult;
import com.unicauca.sga.testService.Domain.Services.ICourseService;
import com.unicauca.sga.testService.Infrastructure.Clients.CourseServiceClient.DTOs.CourseStudentDTOResponse;
import com.unicauca.sga.testService.Infrastructure.Clients.CourseServiceClient.Mappers.CourseStudentDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CourseServiceClient implements ICourseService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;
    private final CourseStudentDTOMapper courseStudentDTOMapper;

    @Override
    public boolean courseExistsById(int id) {
        try {
            restClient.get()
                    .uri("http://course-service/courses/{id}", id)
                    .retrieve()
                    .toBodilessEntity();

            return true;
        } catch (HttpClientErrorException.NotFound ex) {
            return false;
        }
    }

    @Override
    public boolean isStudentInCourse(String studentEmail, int courseId) {
        return Boolean.TRUE.equals(
            restClient.post()
                    .uri("http://course-service/courses/enrollment/validate?studentEmail={email}&courseId={courseId}",
                            studentEmail,
                            courseId)
                    .retrieve()
                    .body(Boolean.class)
        );
    }

    @Override
    public Page<StudentTestResult> getCourseStudents(int courseId, int page, int size) {
        try {
            Map<String, Object> response = restClient.get()
                .uri("http://course-service/courses/enrollment?courseId={courseId}&page={page}&size={size}",
                        courseId,
                        page,
                        size
                )
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

            if (response == null || response.get("content") == null) {
                return Page.empty();
            }

            // Mapear los datos de los estudiantes
            List<StudentTestResult> students = objectMapper.convertValue(
                    response.get("content"),
                    new TypeReference<List<CourseStudentDTOResponse>>() {}
                )
                .stream()
                .map(courseStudentDTOMapper::toModel)
                .toList();

            // Obtener los metadatos del Page
            long totalElements = ((Number) response.get("totalElements")).longValue();
            int pageNumber = ((Number) response.get("number")).intValue();
            int pageSize = ((Number) response.get("size")).intValue();

            // Construir una page con los metadatos obtenidos
            return new PageImpl<>(
                students,
                PageRequest.of(pageNumber, pageSize),
                totalElements
            );

        } catch (HttpClientErrorException.NotFound ex) {
            return Page.empty();
        }
    }
}
