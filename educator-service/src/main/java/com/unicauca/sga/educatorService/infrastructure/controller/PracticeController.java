package com.unicauca.sga.educatorService.infrastructure.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.sga.educatorService.core.entities.Practice;
import com.unicauca.sga.educatorService.core.entities.PracticeMetadata;
import com.unicauca.sga.educatorService.infrastructure.controller.DTO.PracticeMetadataDTO;
import com.unicauca.sga.educatorService.service.PracticeMetadataService;
import com.unicauca.sga.educatorService.service.PracticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/EducatorApi/Practice")
@RequiredArgsConstructor
public class PracticeController {

    @Autowired
    private PracticeMetadataService practiceMetadataService;
    @Autowired
    private PracticeService practiceService;

   
    @PostMapping("/CreatePractice")
    public Practice CreatePractice(@RequestBody Practice practice){
        String PracticeToJson = this.practiceService.practiceToJson(practice);
        System.out.println(practice.getUser().toString());
        PracticeMetadata practiceMetdata = new PracticeMetadata(null,practice.getUser().getId(),new Date(),PracticeToJson,false);
        System.out.println(practice.toString());
        this.practiceMetadataService.createPracticeMetadata(practiceMetdata);
        return practice;
      }

    @GetMapping("/ListPracticeByUserId")
    public List<PracticeMetadataDTO> ListPracticeByUserId(@RequestParam Long userId){
      List<PracticeMetadata> practice  = practiceMetadataService.listPracticesMetadataByUser(userId);
      List<PracticeMetadataDTO> practiceDTO = new ArrayList<>();
      for (PracticeMetadata p : practice) {
        PracticeMetadataDTO data =new PracticeMetadataDTO(p.getPracticeMetadataId(),p.getUserId(),p.getCreationDate(),this.practiceService.jsonToPractice(p.getPractice()),p.getIsAcepted());
        practiceDTO.add(data);
    }
      return practiceDTO;
    }

    @PostMapping("/UpdatePractice")
    public PracticeMetadata updatePractice(@RequestBody PracticeMetadata practice){
     return this.practiceMetadataService.updatePracticeMetada(practice);
    }

    @GetMapping("/ListPratice")
    public List<PracticeMetadataDTO> ListPractice(){

      List<PracticeMetadata> practice  = practiceMetadataService.listPracticesMetada();
      List<PracticeMetadataDTO> practiceDTO = new ArrayList<>();

      for (PracticeMetadata p : practice) {
        PracticeMetadataDTO data =new PracticeMetadataDTO(p.getPracticeMetadataId(),p.getUserId(),p.getCreationDate(),this.practiceService.jsonToPractice(p.getPractice()),p.getIsAcepted());
        practiceDTO.add(data);
    }
      
      return  practiceDTO;
    }




    
}
