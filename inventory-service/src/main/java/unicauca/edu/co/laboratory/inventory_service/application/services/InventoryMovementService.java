package unicauca.edu.co.laboratory.inventory_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.InventoryMovementRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.InventoryMovementResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.InventoryMovementPort;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MovementType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.models.InventoryMovement;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.InventoryMovementEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers.InventoryMovementMapper;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.InventoryMovementRepositoryJPA;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.ReactiveRepositoryJPA;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryMovementService implements InventoryMovementPort {

    private final InventoryMovementRepositoryJPA inventoryMovementRepositoryJPA;
    private final ReactiveRepositoryJPA reactiveRepositoryJPA;
    private final InventoryMovementMapper inventoryMovementMapper;

    @Override
    public List<InventoryMovementResponseDTO> getInventoryMovements() {
        return inventoryMovementRepositoryJPA.findAll().stream()
                .map(inventoryMovementMapper::toDomain)
                .map(inventoryMovementMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<InventoryMovementResponseDTO> getInventoryMovementById(Long id) {
        return inventoryMovementRepositoryJPA.findById(id)
                .map(inventoryMovementMapper::toDomain)
                .map(inventoryMovementMapper::toDTO);
    }

    @Override
    public InventoryMovementResponseDTO saveInventoryMovement(InventoryMovementRequestDTO movement) {
        ReactiveEntity reactive = reactiveRepositoryJPA.findById(movement.getReactiveId())
                .orElseThrow(() -> new RuntimeException("Reactivo no encontrado"));

        if (movement.getMovementType() == MovementType.SALIDA && reactive.getQuantity() < movement.getQuantity()) {
            throw new IllegalArgumentException("No hay suficiente cantidad de reactivo para esta salida");
        }

        double nuevaCantidad = reactive.getQuantity();
        if (movement.getMovementType() == MovementType.ENTRADA) {
            nuevaCantidad += movement.getQuantity();
        } else {
            nuevaCantidad -= movement.getQuantity();
        }

        reactive.setQuantity(nuevaCantidad);

        LocalDateTime hoy = LocalDateTime.now();
        if (reactive.getSafetySheetExpiration().isBefore(hoy)) {
            reactive.setStatus(ReactiveStatus.VENCIDO);
        } else if (nuevaCantidad == 0) {
            reactive.setStatus(ReactiveStatus.AGOTADO);
        } else if (nuevaCantidad < reactive.getMinimumQuantity()) {
            reactive.setStatus(ReactiveStatus.BAJO_STOCK);
        } else {
            reactive.setStatus(ReactiveStatus.DISPONIBLE);
        }

        InventoryMovement movimiento = inventoryMovementMapper.toDomain(movement);
        movimiento.setReactiveId(reactive.getReactiveId());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        movimiento.setChargePerson(username);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime fechaMovimiento = movement.getMovementDate() != null ? movement.getMovementDate() : now;

        if (fechaMovimiento.isAfter(now.plusDays(1))) {
            throw new IllegalArgumentException("La fecha del movimiento no puede estar en el futuro");
        }

        movimiento.setMovementDate(fechaMovimiento);
        movimiento.setMovementDate(movement.getMovementDate() != null ? movement.getMovementDate() : LocalDateTime.now());
        InventoryMovementEntity entity = inventoryMovementMapper.toEntity(movimiento);
        entity.setReactive(reactive);
        entity.setChargePerson(username);

        InventoryMovementEntity saved = inventoryMovementRepositoryJPA.save(entity);
        reactiveRepositoryJPA.save(reactive);

        return inventoryMovementMapper.toDTO(inventoryMovementMapper.toDomain(saved));
    }

    @Override
    public Boolean updateInventoryMovement(Integer id, InventoryMovementRequestDTO movement) {
        InventoryMovementEntity existing = inventoryMovementRepositoryJPA.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));

        ReactiveEntity reactive = reactiveRepositoryJPA.findById(movement.getReactiveId())
                .orElseThrow(() -> new RuntimeException("Reactivo no encontrado"));

        InventoryMovement domain = inventoryMovementMapper.toDomain(movement);
        domain.setMovementId(existing.getMovementId());
        domain.setReactiveId(reactive.getReactiveId());
        domain.setMovementDate(movement.getMovementDate());

        InventoryMovementEntity updated = inventoryMovementMapper.toEntity(domain);
        updated.setReactive(reactive);

        inventoryMovementRepositoryJPA.save(updated);
        return true;
    }

    @Override
    public void deleteInventoryMovement(Integer id) {
        Optional<InventoryMovementEntity> existingMovement = inventoryMovementRepositoryJPA.findById(id.longValue());
        if (existingMovement.isPresent()) {
            InventoryMovementEntity entity = existingMovement.get();
            ReactiveEntity reactive = entity.getReactive();
            double nuevaCantidad = reactive.getQuantity();

            if (entity.getMovementType() == MovementType.ENTRADA) {
                nuevaCantidad -= entity.getQuantity();
            } else {
                nuevaCantidad += entity.getQuantity();
            }

            reactive.setQuantity(nuevaCantidad);
            if (nuevaCantidad < reactive.getMinimumQuantity()) {
                reactive.setStatus(ReactiveStatus.BAJO_STOCK);
            } else if (nuevaCantidad == 0) {
                reactive.setStatus(ReactiveStatus.AGOTADO);
            } else {
                reactive.setStatus(ReactiveStatus.DISPONIBLE);
            }

            inventoryMovementRepositoryJPA.delete(entity);
            reactiveRepositoryJPA.save(reactive);
        }
    }

    @Override
    public List<InventoryMovementResponseDTO> getInventoryMovementsByType(MovementType type) {
        return inventoryMovementRepositoryJPA.findByMovementType(type).stream()
                .map(inventoryMovementMapper::toDomain)
                .map(inventoryMovementMapper::toDTO)
                .toList();
    }

    @Override
    public List<InventoryMovementResponseDTO> getInventoryMovementsByReactive(Integer reactiveId) {
        List<InventoryMovementEntity> entities = inventoryMovementRepositoryJPA
                .findByReactive_ReactiveId(Long.valueOf(reactiveId));

        return entities.stream()
                .map(inventoryMovementMapper::toDomain)
                .map(inventoryMovementMapper::toDTO)
                .toList();
    }
}