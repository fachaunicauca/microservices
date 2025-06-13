package unicauca.edu.co.laboratory.inventory_service.application.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.ReactiveRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ReactiveResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.ReactivePort;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RiskType;
import unicauca.edu.co.laboratory.inventory_service.domain.models.Reactive;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ParentHouseEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers.ReactiveMapper;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.ParentHouseRepositoryJPA;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.ReactiveRepositoryJPA;

import java.nio.file.FileSystemException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactiveService implements ReactivePort {
    private final ReactiveRepositoryJPA reactiveRepositoryJPA;
    private final ParentHouseRepositoryJPA parentHouseRepositoryJPA;
    private final ReactiveMapper reactiveMapper;
    private final CloudinaryService cloudinaryService;

    @Override
    public List<ReactiveResponseDTO> getReactive() {
        return reactiveRepositoryJPA.findAll().stream()
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<ReactiveResponseDTO> getReactiveById(Long reactiveId) {
        return reactiveRepositoryJPA.findById(reactiveId)
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO);
    }

    @Override
    public ReactiveResponseDTO saveReactive(ReactiveRequestDTO reactiveDTO) throws FileSystemException{
        String safetySheetUrl;
        try {
            safetySheetUrl = cloudinaryService.uploadFile(reactiveDTO.getSafetySheet());
        } catch (Exception e) {
            throw new FileSystemException("Error uploading safety sheet: " + e.getMessage());
        }

        Reactive reactive = reactiveMapper.toDomain(reactiveDTO);
        ReactiveEntity reactiveEntity = reactiveMapper.toEntity(reactive);
        reactiveEntity.setSafetySheet(safetySheetUrl);
        reactiveEntity.setSafetySheetUpdate(LocalDateTime.now());
        reactiveEntity.setCreateAt(LocalDateTime.now());
        reactiveEntity.setUpdateAt(LocalDateTime.now());
        reactiveEntity.setSafetySheetExpiration(reactiveDTO.getSafetySheetExpiration());

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
    @Transactional
    public boolean updateReactive(Long id, ReactiveRequestDTO reactive) throws FileSystemException{
        Optional<ReactiveEntity> reactiveExist = reactiveRepositoryJPA.findById(id);
        if (reactiveExist.isPresent()) {
            ReactiveEntity reactiveEntity = reactiveExist.orElseThrow(() -> new RuntimeException("Reactive not found"));

            reactiveMapper.updateEntityFromDto(reactive, reactiveEntity);

            ParentHouseEntity parentHouseEntity = parentHouseRepositoryJPA.findById(reactive.getHouse())
                    .orElseThrow(() -> new RuntimeException("Parent house not found"));
            reactiveEntity.setHouse(parentHouseEntity);

            MultipartFile newSafetySheet = reactive.getSafetySheet();
            if (newSafetySheet != null && !newSafetySheet.isEmpty()) {
                try {
                    String safetySheetUrl = cloudinaryService.uploadFile(newSafetySheet);
                    reactiveEntity.setSafetySheet(safetySheetUrl);
                    reactiveEntity.setSafetySheetUpdate(LocalDateTime.now());
                } catch (Exception e) {
                    throw new FileSystemException("Error uploading safety sheet: " + e.getMessage());
                }
            }

            if (reactive.getSafetySheetExpiration() != null) {
                reactiveEntity.setSafetySheetExpiration(reactive.getSafetySheetExpiration());
            }

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
                .toList();
    }

    @Override
    public List<ReactiveResponseDTO> getReactiveByName(String nombre) {
        return reactiveRepositoryJPA.findByName(nombre).stream()
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO)
                .toList();
    }

    @Override
    public List<ReactiveResponseDTO> getReactiveByRiskType(RiskType risk) {
        return reactiveRepositoryJPA.findByRiskTypesContaining(risk).stream()
                .map(reactiveMapper::toDomain)
                .map(reactiveMapper::toDTO)
                .toList();
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
                .toList();
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
                .toList();
    }
}
