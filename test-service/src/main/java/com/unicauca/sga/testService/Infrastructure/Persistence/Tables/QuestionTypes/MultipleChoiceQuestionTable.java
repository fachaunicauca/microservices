package com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTypes;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTable;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="multipleChoiceQuestion")
@PrimaryKeyJoinColumn(name="questionId")
public class MultipleChoiceQuestionTable extends QuestionTable {
    @OneToMany(mappedBy = "question")
    private List<AnswerTable> answers;
}
