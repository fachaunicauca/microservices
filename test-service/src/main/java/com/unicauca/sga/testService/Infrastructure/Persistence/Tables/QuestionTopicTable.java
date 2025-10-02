package com.unicauca.sga.testService.Infrastructure.Persistence.Tables;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "question_topic")
public class QuestionTopicTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_topic_id")
    private int questionTopicId;

    @Column(name = "qt_description")
    private String qtDescription;

}
