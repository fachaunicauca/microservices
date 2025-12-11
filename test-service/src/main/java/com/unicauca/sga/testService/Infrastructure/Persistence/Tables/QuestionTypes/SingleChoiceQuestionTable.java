package com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTypes;

import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.AnswerTable;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "singleChoiceQuestion")
@PrimaryKeyJoinColumn(name = "questionId")
public class SingleChoiceQuestionTable extends QuestionTable {
    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerTable> answers;
}
