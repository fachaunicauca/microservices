package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RequestReplacementStatus;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.RequestReplacementEntity;

import java.util.List;

@Repository
public interface RequestReplacementRepositoryJPA extends JpaRepository<RequestReplacementEntity, Long> {
    List<RequestReplacementEntity> findByStatus(RequestReplacementStatus status);
    List<RequestReplacementEntity> findByReactive(ReactiveEntity reactive);
}
