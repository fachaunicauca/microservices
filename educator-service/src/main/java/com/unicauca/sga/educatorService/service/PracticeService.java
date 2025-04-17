package com.unicauca.sga.educatorService.service;
import org.springframework.stereotype.Service;
import com.unicauca.sga.educatorService.core.entities.Practice;

import com.google.gson.*;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PracticeService {

    
    public String practiceToJson(Practice practice){
        Gson  gson = new Gson();
        return gson.toJson(practice);
    }

    public Practice jsonToPractice(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Practice.class);
    }
    
}
