package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ParentHouseEntity;

import java.util.Optional;

@Repository
public interface ParentHouseRepositoryJPA extends JpaRepository<ParentHouseEntity, Long> {
    Optional<ParentHouseEntity> findByName(String name);
}
