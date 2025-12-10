package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "answer")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AnswerTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long answerId;

    @ManyToOne
    @JoinColumn(name = "questionId", nullable = false)
    private QuestionTable question;
}
