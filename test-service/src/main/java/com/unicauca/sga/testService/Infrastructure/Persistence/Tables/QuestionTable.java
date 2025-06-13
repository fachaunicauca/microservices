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
    private Long question_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_topic_id", nullable = false)
    private QuestionTopicTable questionTopic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_name", nullable = false)
    private SubjectTable subject;

    @Column
    private String question_title;

    @Column
    private String question_text;

    @Lob
    @Column(name = "question_image")
    private byte[] questionImage;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<AnswerTable> answers = new ArrayList<>();
}
