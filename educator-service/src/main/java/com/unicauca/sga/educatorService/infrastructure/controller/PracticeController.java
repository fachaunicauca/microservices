package com.unicauca.sga.educatorService.infrastructure.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public Practice CreatePractice(@RequestBody Practice practice){
        String PracticeToJson = this.practiceService.practiceToJson(practice);
        System.out.println(practice.getUser().toString());
        PracticeMetadata practiceMetdata = new PracticeMetadata(null,practice.getUser().getId(),new Date(),PracticeToJson,false);
        System.out.println(practice.toString());
        this.practiceMetadataService.createPracticeMetadata(practiceMetdata);
        return practice;
      }

    @GetMapping("/ListPracticeByUserId")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
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
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public PracticeMetadata updatePractice(@RequestBody PracticeMetadata practice){
     return this.practiceMetadataService.updatePracticeMetada(practice);
    }

    @GetMapping("/ListPractice")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_TEACHER')")
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