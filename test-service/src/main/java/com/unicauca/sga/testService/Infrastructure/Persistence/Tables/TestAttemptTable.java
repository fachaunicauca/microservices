package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "testAttempt")
public class TestAttemptTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long testAttemptId;

    @Column(nullable = false)
    private String studentEmail;

    @Column(nullable = false)
    private LocalDateTime testAttemptDate;

    @Column(nullable = false)
    private double testAttemptScore;

    @Column(nullable = false)
    private boolean isFullyScored;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testId",nullable = false)
    private TestTable test;

    @ManyToMany
    @JoinTable(
            name = "testAttemptResponse",
            joinColumns = @JoinColumn(name = "testAttemptId"),
            inverseJoinColumns = @JoinColumn(name = "answerId")
    )
    private List<AnswerTable> studentAnswers;
}
