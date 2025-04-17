package com.unicauca.sga.educatorService.core.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Waste {

    private String wasteType;
    private Eunity wasEunity;
    private String estametedAmount;
    private Date wasteGenerationDate;
    private Date wasteHourGenration;

    
}
