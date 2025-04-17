package com.unicauca.sga.educatorService.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    
    private String name;
    private String code;
    private long id;
    private String email;

}