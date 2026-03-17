package com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionStructureParsers;

import org.w3c.dom.Element;
import java.util.List;

public interface MoodleQStructureParser {
    List<String> getSupportedMoodleTypes();

    String parseMoodleStructure(Element questionEl);
}
