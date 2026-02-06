package com.unicauca.sga.courseService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(nullable = false, length = 1000)
    private String studentFirstName;

    @Column(nullable = false, length = 1000)
    private String studentLastName;

    @Column(nullable = false, length = 1000, unique = true)
    private String studentEmail;
}
