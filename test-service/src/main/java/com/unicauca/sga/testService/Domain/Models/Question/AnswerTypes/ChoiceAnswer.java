package com.unicauca.sga.testService.Domain.Models.Question.AnswerTypes;

import com.fasterxml.jackson.annotation.JsonView;
import com.unicauca.sga.testService.Domain.Models.Question.Views;
import lombok.Data;

@Data
public class ChoiceAnswer{
    @JsonView(Views.Student.class)
    private long id;
    @JsonView(Views.Student.class)
    private String text;
    @JsonView(Views.Teacher.class)
    private boolean correct;
}
