package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.StoragePlaces;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.MaterialEntity;

import java.util.List;

@Repository
public interface MaterialRepositoryJPA extends JpaRepository<MaterialEntity, Long> {
    List<MaterialEntity> findByNameContainingIgnoreCase(String name);
    List<MaterialEntity> findByType(MaterialType type);
    List<MaterialEntity> findByStoragePlace(StoragePlaces storagePlace);
    List<MaterialEntity> findByStatus(MaterialStatus status);

}
