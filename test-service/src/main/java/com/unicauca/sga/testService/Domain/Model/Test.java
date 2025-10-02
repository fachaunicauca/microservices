package com.unicauca.sga.testService.Domain.Model;

import lombok.Data;

import java.sql.Date;

@Data
public class Test {
    private Long testId;
    private Long teacherId;
    private String teacherName;
    private Long studentId;
    private Subject subject;
    private int numOfQuestions;
    private Date testDate;
    private float testScore;
}
