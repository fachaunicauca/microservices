package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.InventoryMovementRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.InventoryMovementResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.domain.models.InventoryMovement;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.InventoryMovementEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;

@Component
public class InventoryMovementMapper {
    public static InventoryMovement toDomain(InventoryMovementEntity entity){
        return new InventoryMovement(
                entity.getMovementId(),
                entity.getReactive().getReactiveId(),
                entity.getMovementType(),
                entity.getQuantity(),
                entity.getMovementDescription(),
                entity.getMovementDate(),
                entity.getChargePerson()
        );
    }

    public static InventoryMovementEntity toEntity(InventoryMovement domain){
        return new InventoryMovementEntity(
                domain.getMovementId(),
                new ReactiveEntity(domain.getReactiveId(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null),
                domain.getMovementType(),
                domain.getQuantity(),
                domain.getMovementDescription(),
                domain.getMovementDate(),
                domain.getChargePerson()
        );
    }

    public static InventoryMovement toDomain(InventoryMovementRequestDTO dto){
        return new InventoryMovement(
                null,
                dto.getReactiveId(),
                dto.getMovementType(),
                dto.getQuantity(),
                dto.getMovementDescription(),
                dto.getMovementDate(),
                dto.getChargePerson()
        );
    }

    public static InventoryMovementResponseDTO toDTO(InventoryMovement domain){
        return new InventoryMovementResponseDTO(
                domain.getMovementId(),
                domain.getReactiveId(),
                domain.getMovementType(),
                domain.getQuantity(),
                domain.getMovementDescription(),
                domain.getMovementDate(),
                domain.getChargePerson()
        );
    }
}
