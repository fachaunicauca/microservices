package unicauca.edu.co.laboratory.inventory_service.application.ports;

import unicauca.edu.co.laboratory.inventory_service.application.dto.request.InventoryMovementRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.InventoryMovementResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MovementType;

import java.util.List;
import java.util.Optional;

public interface InventoryMovementPort {
    List<InventoryMovementResponseDTO> getInventoryMovements();
    Optional<InventoryMovementResponseDTO> getInventoryMovementById(Integer id);
    InventoryMovementResponseDTO saveInventoryMovement(Long id, InventoryMovementRequestDTO movement);
    List<InventoryMovementResponseDTO> getInventoryMovementsByType(MovementType type);
    Optional<InventoryMovementResponseDTO> getInventoryMovementsByReactive(Integer reactiveId);
}
