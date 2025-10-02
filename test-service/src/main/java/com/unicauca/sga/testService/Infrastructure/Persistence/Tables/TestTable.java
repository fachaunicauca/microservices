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
    @Column(name = "test_id")
    private Long testId;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "num_of_questions")
    private int numOfQuestions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_name", nullable = false)
    private SubjectTable subject;

    @Column(nullable = false, name = "test_date")
    private Date testDate;

    @Column(name = "test_score")
    private float testScore;
}
