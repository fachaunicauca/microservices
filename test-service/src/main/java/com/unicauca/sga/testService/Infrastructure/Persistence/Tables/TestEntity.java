package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    @Column(nullable = false)
    private String teacherEmail;

    @Column(unique = true, nullable = false, length = 1000)
    private String testTitle;

    @Column(length = 5000)
    private String testDescription;

    @Column(nullable = false)
    private int testDurationMinutes;

    @Column(nullable = false)
    private int testNumberOfQuestions;

    @Column(nullable = false)
    private int testAttemptLimit;

    @Column(nullable = false)
    private byte testState;

    @Column(nullable = false, name = "isperiodic")
    private boolean periodic;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions;
}
