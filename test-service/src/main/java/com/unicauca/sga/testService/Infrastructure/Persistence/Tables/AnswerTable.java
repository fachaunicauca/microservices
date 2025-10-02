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
    @Column(name = "answer_id")
    private Long answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionTable question;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "answer_is_correct")
    private boolean correct;
}
