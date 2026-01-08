package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "studentTestConfig")
public class StudentTestConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentTestConfigId;

    @Column(nullable = false)
    private String studentEmail;

    @Column(nullable = false)
    private int attemptLimit;

    @Column(nullable = false)
    private int attemptsUsed;

    private LocalDateTime lastAttemptAt;

    private Double finalScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testId", nullable = false)
    private TestEntity test;
}
