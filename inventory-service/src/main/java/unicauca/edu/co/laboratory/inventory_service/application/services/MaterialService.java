package unicauca.edu.co.laboratory.inventory_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.MaterialRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.MaterialResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.MaterialPort;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.StoragePlaces;
import unicauca.edu.co.laboratory.inventory_service.domain.exceptions.AlreadyExistException;
import unicauca.edu.co.laboratory.inventory_service.domain.exceptions.NotFoundException;
import unicauca.edu.co.laboratory.inventory_service.domain.models.Material;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.MaterialEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers.MaterialMapper;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.MaterialRepositoryJPA;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialService implements MaterialPort {
    private final MaterialRepositoryJPA materialRepositoryJPA;
    private final MaterialMapper materialMapper;

    @Override
    public List<MaterialResponseDTO> getMaterials() {
        return materialRepositoryJPA.findAll().stream()
                .map(materialMapper::toDomain)
                .map(materialMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<MaterialResponseDTO> getMaterialById(Long materialId) {
        return materialRepositoryJPA.findById(materialId)
                .map(materialMapper::toDomain)
                .map(materialMapper::toDTO);
    }

    @Override
    public MaterialResponseDTO saveMaterial(MaterialRequestDTO material) {
        Material materialDomain = materialMapper.toDomain(material);
        MaterialEntity materialEntity = materialRepositoryJPA.save(materialMapper.toEntity(materialDomain));
        return materialMapper.toDTO(materialMapper.toDomain(materialEntity));
    }

    @Override
    public boolean deleteMaterial(Long materialId) {
        if (!materialRepositoryJPA.existsById(materialId)) return false;
        materialRepositoryJPA.deleteById(materialId);
        return true;
    }

    @Override
    public boolean updateMaterial(Long id, MaterialRequestDTO material) {
        Optional<MaterialEntity> optionalMaterial = materialRepositoryJPA.findById(id);
        if (optionalMaterial.isEmpty()) {
            throw new NotFoundException("Material with id " + id + " not found");
        }
        String newFormattedCapacity = material.getCapacity() + " " + material.getMeasureUnit().name();
        List<MaterialEntity> materialsWithSameName = materialRepositoryJPA.findByNameContainingIgnoreCase(material.getName());
        boolean conflict = materialsWithSameName.stream()
                .anyMatch(existing ->
                        !existing.getMaterialId().equals(id)
                                && existing.getCapacity().equalsIgnoreCase(newFormattedCapacity)
                );
        if (conflict) {
            throw new AlreadyExistException("Ya existe otro material con el nombre '"
                    + material.getName() + "' y capacidad " + newFormattedCapacity);
        }
        MaterialEntity existingEntity = optionalMaterial.get();
        Material updatedDomain = materialMapper.toDomain(existingEntity);

        updatedDomain.setName(material.getName());
        updatedDomain.setType(material.getType());
        updatedDomain.setCapacity(newFormattedCapacity);
        updatedDomain.setQuantity(material.getQuantity());
        updatedDomain.setStoragePlace(material.getStoragePlace());
        updatedDomain.setStatus(material.getStatus());
        updatedDomain.setObservation(material.getObservation());

        materialRepositoryJPA.save(materialMapper.toEntity(updatedDomain));
        return true;
    }

    @Override
    public List<MaterialResponseDTO> getMaterialByName(String name) {
        return materialRepositoryJPA.findByNameContainingIgnoreCase(name).stream()
                .map(materialMapper::toDomain)
                .map(materialMapper::toDTO)
                .toList();
    }

    @Override
    public List<MaterialResponseDTO> getMaterialByType(MaterialType type) {
        return materialRepositoryJPA.findByType(type).stream()
                .map(materialMapper::toDomain)
                .map(materialMapper::toDTO)
                .toList();
    }

    @Override
    public List<MaterialResponseDTO> getMaterialByStoragePlace(StoragePlaces place) {
        return materialRepositoryJPA.findByStoragePlace(place).stream()
                .map(materialMapper::toDomain)
                .map(materialMapper::toDTO)
                .toList();
    }

    @Override
    public List<MaterialResponseDTO> getMaterialByStatus(MaterialStatus status) {
        return materialRepositoryJPA.findByStatus(status).stream()
                .map(materialMapper::toDomain)
                .map(materialMapper::toDTO)
                .toList();
    }
}
