package com.unicauca.sga.educatorService.infrastructure.persitence.Entitys;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "practice_metadata")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PracticeMetadataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "practice_metadata_id") // Nuevo nombre de columna
    private Long practiceMetadataId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Lob
    @Column(name = "practice", nullable = false)
    private String practice; // O usa JsonNode si prefieres manejarlo como JSON

    @Column(name = "is_accepted", nullable = false)
    private Boolean isAccepted;


    
}
