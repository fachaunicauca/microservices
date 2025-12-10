package com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTypes;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "openEndedQuestion")
@PrimaryKeyJoinColumn(name = "questionId")
public class OpenEndedQuestionTable extends QuestionTable {
}
