package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "question")
public class QuestionTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_topic_id", nullable = false)
    private QuestionTopicTable questionTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_name", nullable = false)
    private SubjectTable subject;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "question_text")
    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AnswerTable> answers = new ArrayList<>();
}
