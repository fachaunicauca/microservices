package com.unicauca.sga.courseService.Domain.Models;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    private Integer courseId;
    private String courseName;
    private String teacherEmail;
    private String courseDescription;
}
