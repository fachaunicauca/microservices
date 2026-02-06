package com.unicauca.sga.courseService.Infrastructure.Controllers.EnrollStudentController.DTOs.Response;
import lombok.Data;

@Data
public class StudentEnrollmentDTOResponse {
    private Long studentEnrollmentId;
    private Integer courseId;
    private Long studentId;
}
