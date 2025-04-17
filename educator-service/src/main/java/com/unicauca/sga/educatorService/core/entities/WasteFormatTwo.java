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
public class WasteFormatTwo{

    private String wasteType;
    private Eunity wasEunity;
    private String estametedAmount;
    private String generatedAmount;
    private String observation;

    
}
