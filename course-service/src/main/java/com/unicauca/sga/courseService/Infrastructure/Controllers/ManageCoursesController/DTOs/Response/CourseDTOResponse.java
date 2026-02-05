package com.unicauca.sga.courseService.Infrastructure.Controllers.ManageCoursesController.DTOs.Response;

import lombok.Data;

@Data
public class CourseDTOResponse {
    private Integer courseId;
    private String courseName;
    private String teacherEmail;
    private String courseDescription;
}
