package com.unicauca.sga.testService.Infrastructure.Clients;

import com.unicauca.sga.testService.Aplication.Services.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class CourseServiceClient implements ICourseService {

    private final RestClient restClient;

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
}
