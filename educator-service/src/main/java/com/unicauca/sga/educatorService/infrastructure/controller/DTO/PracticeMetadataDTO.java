package com.unicauca.sga.educatorService.infrastructure.controller.DTO;
import java.util.Date;

import com.unicauca.sga.educatorService.core.entities.Practice;

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
public class PracticeMetadataDTO {
    
    private Long practiceMetadataId;
    private Long userId;
    private Date creationDate;
    private Practice practice ;
    private Boolean isAcepted;
        

}
