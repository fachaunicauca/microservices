package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "test")
public class TestTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testId;

    @Column(nullable = false)
    private String teacherEmail;

    @Column(unique = true, nullable = false)
    private String testTitle;

    private String testDescription;

    @Column(nullable = false)
    private int testDurationMinutes;

    @Column(nullable = false)
    private int testNumberOfQuestions;

    @Column(nullable = false)
    private int testAttemptLimit;

    @Column(nullable = false)
    private byte testState;

    @Column(nullable = false)
    private boolean isPeriodic;

    @OneToMany(mappedBy = "test")
    private List<QuestionTable> questions;
}
