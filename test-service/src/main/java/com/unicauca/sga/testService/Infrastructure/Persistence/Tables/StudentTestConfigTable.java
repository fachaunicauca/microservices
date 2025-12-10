package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "studentTestConfig")
public class StudentTestConfigTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentTestConfigId;

    @Column(nullable = false)
    private String studentEmail;

    @Column(nullable = false)
    private int attemptLimit;

    @Column(nullable = false)
    private int attemptsUsed;

    @ManyToOne
    @JoinColumn(name = "testId", nullable = false)
    private TestTable test;
}
