package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "question")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class QuestionTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long questionId;

    @Column(nullable = false)
    private String questionText;

    private String questionTitle;

    private byte[] questionImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testId", nullable = false)
    private TestTable test;
}
