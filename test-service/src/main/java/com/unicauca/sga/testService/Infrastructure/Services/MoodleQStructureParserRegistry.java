package com.unicauca.sga.testService.Infrastructure.Services;

import com.unicauca.sga.testService.Infrastructure.Services.MoodleQuestionStructureParsers.MoodleQStructureParser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MoodleQStructureParserRegistry {
    private final Map<String, MoodleQStructureParser> moodleQStructureParsers = new HashMap<>();

    public MoodleQStructureParserRegistry(List<MoodleQStructureParser> moodleQStructureParsers) {
        for(MoodleQStructureParser parser : moodleQStructureParsers){
            for(String supportedType: parser.getSupportedMoodleTypes()){
                this.moodleQStructureParsers.put(supportedType, parser);
            }
        }
    }

    public MoodleQStructureParser get(String moodleType){
        return moodleQStructureParsers.get(moodleType);
    }

    public boolean hasSupport(String moodleType){
        return moodleQStructureParsers.containsKey(moodleType);
    }
}
