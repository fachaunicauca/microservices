package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "studentResponse")
public class StudentResponseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentResponseId;

    @Column(nullable = false)
    private String response;

    private int score;

    @Column(nullable = false)
    private boolean isGraded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testAttemptId", nullable = false)
    private TestAttemptEntity testAttempt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId",  nullable = false)
    private QuestionEntity question;
}
