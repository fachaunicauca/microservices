package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "testAttempt")
public class TestAttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testAttemptId;

    @Column(nullable = false)
    private String studentEmail;

    @Column(nullable = false)
    private LocalDateTime testAttemptDate;

    @Column(nullable = false)
    private double testAttemptScore;

    @Column(nullable = false)
    private boolean fullyScored;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testId",nullable = false)
    private TestEntity test;

    @OneToMany()
    private List<StudentResponseEntity> studentResponses;
}
