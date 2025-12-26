package com.unicauca.sga.testService.Domain.Models.Question;

import com.unicauca.sga.testService.Domain.Models.Test;
import lombok.Data;

@Data
public class Question{
    private Long questionId;
    private String questionText;
    private String questionTitle;
    private String questionImageUrl;
    private String questionType;
    private String questionStructure;

    private Test test;
}
