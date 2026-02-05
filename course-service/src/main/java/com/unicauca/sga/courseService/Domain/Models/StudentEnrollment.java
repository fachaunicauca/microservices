package com.unicauca.sga.courseService.Domain.Models;

import lombok.Data;

@Data
public class StudentEnrollment {
    private Long studentEnrollmentId;
    private Course course;
    private Student student;

    public StudentEnrollment(Student student, Course course){
        this.student = student;
        this.course = course;
    }
}
