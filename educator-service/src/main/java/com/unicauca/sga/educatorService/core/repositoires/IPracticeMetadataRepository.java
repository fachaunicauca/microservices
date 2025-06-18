package com.unicauca.sga.educatorService.core.repositoires;

import java.util.List;

import com.unicauca.sga.educatorService.core.entities.PracticeMetadata;

public interface IPracticeMetadataRepository {

    PracticeMetadata createPracticeMetadata(PracticeMetadata practiceMetdata);
    PracticeMetadata delatePracticeMetadata(PracticeMetadata practiceMetdata);
    PracticeMetadata updatePracticeMetdata(PracticeMetadata practiceMetdata);
    List<PracticeMetadata> listPractices();
    List<PracticeMetadata> listPracticesbyUser(Long userId);

    
} 
