package com.unicauca.sga.testService.Domain.Services;

import com.unicauca.sga.testService.Domain.Models.Question.Question;

import java.io.InputStream;
import java.util.List;

public interface IMoodleQuestionParser {
    List<Question> parseMoodleQuestions(InputStream xmlStream, List<Integer> selectedIndexes, int testId);
}
