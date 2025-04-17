package com.unicauca.sga.educatorService.core.repositoires;

import java.util.List;

import com.unicauca.sga.educatorService.core.entities.PracticeMetadata;
import com.unicauca.sga.educatorService.core.entities.User;

public interface iPracticeMetadataRepository {

    PracticeMetadata createPracticeMetadata(PracticeMetadata practiceMetdata);
    PracticeMetadata delatePracticeMetadata(PracticeMetadata practiceMetdata);
    PracticeMetadata updatePracticeMetdata(PracticeMetadata practiceMetdata);
    List<PracticeMetadata> listPractices();
    List<PracticeMetadata> listPracticesbyUser(Long userId);

    
} 
