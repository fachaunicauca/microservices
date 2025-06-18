package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.InventoryMovementRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.InventoryMovementResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.domain.models.InventoryMovement;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.InventoryMovementEntity;

@Mapper(componentModel = "spring")
public interface InventoryMovementMapper {
    @Mapping(source = "reactive.reactiveId", target = "reactiveId")
    InventoryMovement toDomain(InventoryMovementEntity entity);

    @Mapping(target = "reactive", expression = "java(new ReactiveEntity(domain.getReactiveId(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))")
    InventoryMovementEntity toEntity(InventoryMovement domain);

    @Mapping(target = "movementId", ignore = true)
    InventoryMovement toDomain(InventoryMovementRequestDTO dto);

    InventoryMovementResponseDTO toDTO(InventoryMovement domain);
}
