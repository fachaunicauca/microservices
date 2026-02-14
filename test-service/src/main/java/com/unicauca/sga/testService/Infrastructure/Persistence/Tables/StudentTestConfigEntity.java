package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import com.unicauca.sga.testService.Domain.Enums.AttemptRequestStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "studentTestConfig",
        indexes = {
                @Index(name = "idx_test_student", columnList = "testId, studentEmail")
        }
)
public class StudentTestConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentTestConfigId;

    @Column(nullable = false)
    private String studentEmail;

    @Column(nullable = false)
    private int totalAttemptsUsed;

    @Column(nullable = false)
    private int attemptsUsed;

    private LocalDateTime lastAttemptAt;

    private Double finalScore;

    @Column(nullable = false)
    private String attemptRequestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testId", nullable = false)
    private TestEntity test;
}
