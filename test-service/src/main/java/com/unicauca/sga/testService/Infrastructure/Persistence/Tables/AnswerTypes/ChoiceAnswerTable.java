package com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTypes;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "choiceAnswer")
@PrimaryKeyJoinColumn(name = "answerId")
public class ChoiceAnswerTable extends AnswerTable {
    @Column(nullable = false)
    private String answerText;
    @Column(nullable = false)
    private boolean isCorrect;
}
