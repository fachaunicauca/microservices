package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "answer")
public class AnswerTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionTable question;

    @Column
    private String answer_text;

    @Column
    private boolean answer_isCorrect;
}
