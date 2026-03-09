package com.unicauca.sga.courseService.Domain.Models;

import lombok.Data;

@Data
public class Course {
    private Integer courseId;
    private String courseName;
    private String teacherEmail;
    private String courseGroup;
}
