package com.unicauca.sga.testService.Domain.Models;

import com.unicauca.sga.testService.Domain.Constants.TestState;
import com.unicauca.sga.testService.Domain.Models.Question.Question;
import lombok.Data;

import java.util.List;

@Data
public class Test {
    private Integer testId;
    private String teacherEmail;
    private String testTitle;
    private String testDescription;
    private int testDurationMinutes;
    private int testNumberOfQuestions;
    private int testAttemptLimit;
    private byte testState;
    private boolean periodic;

    private Integer courseId;
    private List<Question> questions;

    public boolean hasEnoughQuestions(long totalQuestions){
        return totalQuestions >= this.testNumberOfQuestions;
    }

    public boolean isActive(){
        return testState == TestState.ACTIVE;
    }

    public Test toStudentView(List<Question> questions){
        Test studentTest = new Test();
        studentTest.setTestId(this.getTestId());
        studentTest.setTestTitle(this.getTestTitle());
        studentTest.setTestDurationMinutes(this.getTestDurationMinutes());
        studentTest.setTestNumberOfQuestions(this.getTestNumberOfQuestions());
        studentTest.setQuestions(questions);

        return studentTest;
    }
}
