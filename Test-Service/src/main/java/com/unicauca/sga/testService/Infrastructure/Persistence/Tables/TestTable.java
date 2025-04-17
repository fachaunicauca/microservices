package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "test")
public class TestTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long test_id;

    @Column
    private Long teacher_id;

    @Column
    private String teacher_name;

    @Column
    private Long student_id;

    @Column
    private int num_of_questions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_name", nullable = false)
    private SubjectTable subject;

    @Column(nullable = false)
    private Date test_date;

    @Column
    private float test_score;
}
