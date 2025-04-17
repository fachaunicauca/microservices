package com.unicauca.sga.educatorService.infrastructure.persitence.RepositoryJPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.unicauca.sga.educatorService.infrastructure.persitence.Entitys.PracticeMetadataEntity;


public interface PracticeMetadataRepositoryJPA extends JpaRepository<PracticeMetadataEntity,Long> {
    List<PracticeMetadataEntity> findByUserId(Long userId);
}
