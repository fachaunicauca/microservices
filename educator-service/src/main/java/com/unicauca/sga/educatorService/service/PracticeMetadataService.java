package com.unicauca.sga.educatorService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicauca.sga.educatorService.core.entities.Practice;
import com.unicauca.sga.educatorService.core.entities.PracticeMetadata;
import com.unicauca.sga.educatorService.core.repositoires.iPracticeMetadataRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PracticeMetadataService {

   @Autowired
   private iPracticeMetadataRepository ipracticeMetadataRepository;

    public PracticeMetadata createPracticeMetadata(PracticeMetadata metdata){ 
        System.out.println("Este es el ID del usuario:"+metdata.getUserId());
        ipracticeMetadataRepository.createPracticeMetadata(metdata);
        return metdata;
    };
  public  Practice delatePracticeMetada(PracticeMetadata practice){
        throw new UnsupportedOperationException("Not Implemented");
    };
   public PracticeMetadata updatePracticeMetada(PracticeMetadata practice){
        ipracticeMetadataRepository.updatePracticeMetdata(practice);
        return practice;
    };
  public List<PracticeMetadata> listPracticesMetada(){
        return ipracticeMetadataRepository.listPractices();
    };
   public List<PracticeMetadata> listPracticesMetadataByUser(Long userId){
        return ipracticeMetadataRepository.listPracticesbyUser(userId);
    };

    

}
