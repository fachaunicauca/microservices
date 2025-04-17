package com.unicauca.sga.educatorService.infrastructure.persitence.Mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.unicauca.sga.educatorService.core.entities.PracticeMetadata;
import com.unicauca.sga.educatorService.infrastructure.persitence.Entitys.PracticeMetadataEntity;

@Mapper(componentModel = "spring")
@Component
public interface practiceMetadataMapper{

    @Mapping(source = "practiceMetadataId", target = "practiceMetadataId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "practice", target = "practice")
    @Mapping(source = "isAccepted", target = "isAcepted")
    PracticeMetadata toPracticeMetadata(PracticeMetadataEntity practiceMetadataEntity);

    @Mapping(source = "practiceMetadataId", target = "practiceMetadataId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "practice", target = "practice")
    @Mapping(source = "isAcepted", target = "isAccepted")
    PracticeMetadataEntity toPracticeMetadataEntity(PracticeMetadata practiceMetdata);

    List<PracticeMetadata> toPracticeMetadataList(List<PracticeMetadataEntity> practiceMetadataEntityList);
    List<PracticeMetadataEntity> toPracticeMetadataEntitieList(List<PracticeMetadata> PracticeMetadataList);
    
} 
