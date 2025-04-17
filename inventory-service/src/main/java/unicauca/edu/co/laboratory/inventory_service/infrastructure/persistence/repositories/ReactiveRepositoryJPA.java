package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RiskType;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ParentHouseEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReactiveRepositoryJPA extends JpaRepository<ReactiveEntity, Long> {
    List<ReactiveEntity> findByStatus(ReactiveStatus status);
    Optional<ReactiveEntity> findByName(String name);
    List<ReactiveEntity> findByRiskTypesContaining(RiskType riskType);
    Optional<ReactiveEntity> findByCode(String code);
    List<ReactiveEntity> findByType(ReactiveType type);
    List<ReactiveEntity> findByHouse(Optional<ParentHouseEntity> house);
}
