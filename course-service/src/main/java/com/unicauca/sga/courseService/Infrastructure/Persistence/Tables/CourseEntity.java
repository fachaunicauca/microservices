package com.unicauca.sga.courseService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(length = 1000, nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String teacherEmail;

    @Column(length = 5000)
    private String courseDescription;
}
