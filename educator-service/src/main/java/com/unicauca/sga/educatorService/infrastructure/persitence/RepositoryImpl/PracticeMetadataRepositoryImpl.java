package com.unicauca.sga.educatorService.infrastructure.persitence.RepositoryImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.unicauca.sga.educatorService.core.entities.PracticeMetadata;
import com.unicauca.sga.educatorService.core.repositoires.IPracticeMetadataRepository;
import com.unicauca.sga.educatorService.infrastructure.persitence.RepositoryJPA.PracticeMetadataRepositoryJPA;

import lombok.RequiredArgsConstructor;

import com.unicauca.sga.educatorService.infrastructure.persitence.Entitys.PracticeMetadataEntity;
import com.unicauca.sga.educatorService.infrastructure.persitence.Mappers.practiceMetadataMapper;

@Component
@RequiredArgsConstructor
public class PracticeMetadataRepositoryImpl implements IPracticeMetadataRepository {

    //@Autowired
    //@Lazy
    private final PracticeMetadataRepositoryJPA practiceMetadataRepository;

    private final practiceMetadataMapper practiceMetadataMapper;

    @Override
    public PracticeMetadata createPracticeMetadata(PracticeMetadata practiceMetdata) {
        PracticeMetadataEntity practice = practiceMetadataMapper.toPracticeMetadataEntity(practiceMetdata);
        practiceMetadataRepository.saveAndFlush(practice);
        return practiceMetdata;
    }

    @Override
    public PracticeMetadata delatePracticeMetadata(PracticeMetadata practiceMetdata) {
        practiceMetadataRepository.delete(practiceMetadataMapper.toPracticeMetadataEntity(practiceMetdata));
        return practiceMetdata;
    }

    @Override
    public PracticeMetadata updatePracticeMetdata(PracticeMetadata practiceMetdata) {
        Optional<PracticeMetadataEntity> practice = practiceMetadataRepository.findById(practiceMetdata.getPracticeMetadataId());
        if(practice.isPresent()){
            practiceMetadataRepository.save(practiceMetadataMapper.toPracticeMetadataEntity(practiceMetdata));
        }
        return practice.isPresent() ? practiceMetdata : null;
    }

    @Override
    public List<PracticeMetadata> listPractices() {
        return practiceMetadataMapper.toPracticeMetadataList(practiceMetadataRepository.findAll());
    }

    @Override
    public List<PracticeMetadata> listPracticesbyUser(Long userId) {
        return practiceMetadataMapper.toPracticeMetadataList(practiceMetadataRepository.findByUserId(userId));
    }


    
}
