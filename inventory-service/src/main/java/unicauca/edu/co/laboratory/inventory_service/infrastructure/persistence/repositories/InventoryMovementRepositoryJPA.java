package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MovementType;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.InventoryMovementEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;

import java.util.List;

@Repository
public interface InventoryMovementRepositoryJPA extends JpaRepository<InventoryMovementEntity, Long> {
    List<InventoryMovementEntity> findByReactive(ReactiveEntity reactive);
    List<InventoryMovementEntity> findByMovementType(MovementType movementType);
    List<InventoryMovementEntity> findByReactive_ReactiveId(Long reactiveId);
}
