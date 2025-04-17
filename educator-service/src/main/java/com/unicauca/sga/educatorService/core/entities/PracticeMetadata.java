package com.unicauca.sga.educatorService.core.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class PracticeMetadata {

    private Long practiceMetadataId;
    private Long userId;
    private Date creationDate;
    private String practice;
    private Boolean isAcepted;
        
    
}
