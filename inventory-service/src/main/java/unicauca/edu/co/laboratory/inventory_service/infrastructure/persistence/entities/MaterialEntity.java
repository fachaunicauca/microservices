package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.StoragePlaces;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "material")
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id", unique = true, nullable = false, updatable = false)
    private Long materialId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType type;

    @Column(name = "capacity", nullable = false, length = 100)
    private String capacity;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "storage_place", nullable = false)
    @Enumerated(EnumType.STRING)
    private StoragePlaces storagePlace;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialStatus status;

    @Column(name = "observation", length = 255)
    private String observation;

    @CreationTimestamp
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;
}
