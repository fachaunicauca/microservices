package unicauca.edu.co.laboratory.inventory_service.application.ports;

import unicauca.edu.co.laboratory.inventory_service.application.dto.request.MaterialRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.MaterialResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.StoragePlaces;

import java.util.List;
import java.util.Optional;

public interface MaterialPort {
    List<MaterialResponseDTO> getMaterials();
    Optional<MaterialResponseDTO> getMaterialById(Long materialId);
    MaterialResponseDTO saveMaterial(MaterialRequestDTO material);
    boolean deleteMaterial(Long materialId);
    boolean updateMaterial(Long id, MaterialRequestDTO material);
    List<MaterialResponseDTO> getMaterialByName(String name);
    List<MaterialResponseDTO> getMaterialByType(MaterialType type);
    List<MaterialResponseDTO> getMaterialByStoragePlace(StoragePlaces place);
    List<MaterialResponseDTO> getMaterialByStatus(MaterialStatus status);
}
