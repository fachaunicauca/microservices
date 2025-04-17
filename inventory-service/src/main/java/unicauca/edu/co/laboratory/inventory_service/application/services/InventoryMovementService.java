package unicauca.edu.co.laboratory.inventory_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.InventoryMovementRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.InventoryMovementResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.InventoryMovementPort;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MovementType;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryMovementService implements InventoryMovementPort {
    @Override
    public List<InventoryMovementResponseDTO> getInventoryMovements() {
        return List.of();
    }

    @Override
    public Optional<InventoryMovementResponseDTO> getInventoryMovementById(Integer id) {
        return Optional.empty();
    }

    @Override
    public InventoryMovementResponseDTO saveInventoryMovement(Long id, InventoryMovementRequestDTO movement) {
        return null;
    }

    @Override
    public List<InventoryMovementResponseDTO> getInventoryMovementsByType(MovementType type) {
        return List.of();
    }

    @Override
    public Optional<InventoryMovementResponseDTO> getInventoryMovementsByReactive(Integer reactiveId) {
        return Optional.empty();
    }
}
