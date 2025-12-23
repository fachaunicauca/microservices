package com.unicauca.sga.testService.Domain.Models.Question;

import com.unicauca.sga.testService.Domain.Models.Test;
import lombok.Data;

@Data
public class Question{
    private long questionId;
    private String questionText;
    private String questionTitle;
    private byte[] questionImage;
    private String questionType;
    private String questionStructure;

    private Test test;
}
