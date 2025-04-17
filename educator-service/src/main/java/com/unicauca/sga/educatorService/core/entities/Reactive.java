package com.unicauca.sga.educatorService.core.entities;

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
public class Reactive {

    private String sustanceName;
    private String code;
    private Eunity unity;
    private long amount;
    private long concentration;
    private int reactiveID;
    
}
