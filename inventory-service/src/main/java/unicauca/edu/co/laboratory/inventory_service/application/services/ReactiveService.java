package unicauca.edu.co.laboratory.inventory_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.ReactiveRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ParentHouseResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ReactiveResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.ReactivePort;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RiskType;
import unicauca.edu.co.laboratory.inventory_service.domain.models.Reactive;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ParentHouseEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers.ParentHouseMapper;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers.ReactiveMapper;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.ParentHouseRepositoryJPA;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.ReactiveRepositoryJPA;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactiveService implements ReactivePort {
    private final ReactiveRepositoryJPA reactiveRepositoryJPA;
    private final ParentHouseRepositoryJPA parentHouseRepositoryJPA;
    private final ReactiveMapper reactiveMapper;
    private final ParentHouseMapper parentHouseMapper;

    @Override
    public List<ReactiveResponseDTO> getReactive() {
        return reactiveRepositoryJPA.findAll().stream()
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReactiveResponseDTO> getReactiveById(Long reactiveId) {
        return reactiveRepositoryJPA.findById(reactiveId)
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO);
    }

    @Override
    public ReactiveResponseDTO saveReactive(ReactiveRequestDTO reactiveDTO) {
        Reactive reactive = reactiveMapper.toDomain(reactiveDTO);
        ReactiveEntity reactiveEntity = reactiveMapper.toEntity(reactive);
        reactiveEntity.setSafetySheetUpdate(LocalDateTime.now());
        reactiveEntity.setCreateAt(LocalDateTime.now());
        reactiveEntity.setUpdateAt(LocalDateTime.now());

        ParentHouseResponseDTO parentHouseResponseDTO = parentHouseRepositoryJPA.findById(reactiveDTO.getHouse())
                .map(parentHouseMapper::toDomain)
                .map(parentHouseMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Parent house not found"));

        return reactiveMapper.toDTO(
                reactiveMapper.toDomain(
                        reactiveRepositoryJPA.save(reactiveEntity)));
    }

    @Override
    public boolean deleteReactive(Long reactiveId) {
        if (reactiveRepositoryJPA.existsById(reactiveId)){
            reactiveRepositoryJPA.deleteById(reactiveId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateReactive(Long id, ReactiveRequestDTO reactive) {
        Optional<ReactiveEntity> reactiveExist = reactiveRepositoryJPA.findById(id);
        if (reactiveExist.isPresent()) {
            ReactiveEntity reactiveEntity = reactiveExist.orElseThrow(() -> new RuntimeException("Reactive not found"));
            reactiveEntity.setName(reactive.getName());
            reactiveEntity.getHouse().setParentHouseId(reactive.getHouse());
            reactiveEntity.setType(reactive.getType());
            reactiveEntity.setFormula(reactive.getFormula());
            reactiveEntity.setCode(reactive.getCode());
            reactiveEntity.setQuantity(reactive.getQuantity());
            reactiveEntity.setMinimumQuantity(reactive.getMinimumQuantity());
            reactiveEntity.setMeasureUnit(reactive.getMeasureUnit());
            reactiveEntity.setStatus(reactive.getStatus());
            if (reactive.getSafetySheet() != null && !reactive.getSafetySheet().equals(reactiveEntity.getSafetySheet())) {
                reactiveEntity.setSafetySheet(reactive.getSafetySheet());
                reactiveEntity.setSafetySheetUpdate(LocalDateTime.now());
            }
            reactiveEntity.setRiskTypes(reactive.getRiskTypes());
            reactiveEntity.setUpdateAt(LocalDateTime.now());
            reactiveRepositoryJPA.save(reactiveEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ReactiveResponseDTO> getReactiveByStatus(ReactiveStatus status) {
        return reactiveRepositoryJPA.findByStatus(status).stream()
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReactiveResponseDTO> getReactiveByName(String nombre) {
        return reactiveRepositoryJPA.findByName(nombre).stream()
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReactiveResponseDTO> getReactiveByRiskType(RiskType risk) {
        return reactiveRepositoryJPA.findByRiskTypesContaining(risk).stream()
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReactiveResponseDTO> getReactiveByCode(String code) {
        return reactiveRepositoryJPA.findByCode(code)
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO);
    }

    @Override
    public List<ReactiveResponseDTO> getReactiveByType(ReactiveType type) {
        return reactiveRepositoryJPA.findByType(type).stream()
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReactiveResponseDTO> getReactiveByParent(Long parentId) {
        Optional<ParentHouseEntity> parentHouseEntity = parentHouseRepositoryJPA.findById(parentId);
        if (parentHouseEntity.isEmpty()) {
            throw new RuntimeException("Parent house not found");
        }
        return reactiveRepositoryJPA.findByHouse(parentHouseEntity).stream()
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO)
                .collect(Collectors.toList());
    }
}
