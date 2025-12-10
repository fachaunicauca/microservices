package com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTypes;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "openEndedAnswer")
@PrimaryKeyJoinColumn(name = "answerId")
public class OpenEndedAnswerTable extends AnswerTable {
    @Column(nullable = false)
    private String studentEmail;
    @Column(nullable = false)
    private String studentAnswer;
}
