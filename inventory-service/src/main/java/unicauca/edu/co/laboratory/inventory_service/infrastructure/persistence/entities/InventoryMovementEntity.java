package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MovementType;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "inventory_movement")
public class InventoryMovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movement_id", unique = true, nullable = false, updatable = false)
    private Long movementId;

    @ManyToOne
    @JoinColumn(name = "reactive_id", nullable = false, referencedColumnName = "reactive_id", updatable = false)
    private ReactiveEntity reactive;

    @Column(name = "movement_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    @Column(nullable = false)
    private Double quantity;

    @Column(name = "movement_description", nullable = false, length = 255)
    private String movementDescription;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "movement_date", nullable = false)
    private LocalDateTime movementDate;

    @Column(name = "charge_person", nullable = false)
    private Integer chargePerson;
}
