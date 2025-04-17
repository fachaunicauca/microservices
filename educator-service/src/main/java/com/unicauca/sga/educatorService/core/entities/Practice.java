package com.unicauca.sga.educatorService.core.entities;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Practice {
    
    private double practiceID;
    private User user;
    private int numberOfEstudents;
    private String practiceName;
    private Date practiceDate;
    private ArrayList<Material> materials;
    private ArrayList<Reactive> reactives;
    private ArrayList<Waste> wastes;



}
