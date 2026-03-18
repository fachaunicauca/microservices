package com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionStructureParsers;

import com.unicauca.sga.testService.Domain.Models.Question.Question;
import org.w3c.dom.Element;
import java.util.List;

public interface MoodleQStructureParser {
    List<String> getSupportedMoodleTypes();

    String parseMoodleStructure(Element questionEl);

    String parseQuestionStructure(Question question);
}
