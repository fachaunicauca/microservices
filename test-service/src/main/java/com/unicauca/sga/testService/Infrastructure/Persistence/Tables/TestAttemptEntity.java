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
    private Integer testAttemptNumberOfQuestions;

    @Column(nullable = false)
    private Double testAttemptScore;

    @Column(nullable = false)
    private Boolean fullyScored;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testId",nullable = false)
    private TestEntity test;

    @OneToMany(mappedBy = "testAttempt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentResponseEntity> studentResponses;
}
