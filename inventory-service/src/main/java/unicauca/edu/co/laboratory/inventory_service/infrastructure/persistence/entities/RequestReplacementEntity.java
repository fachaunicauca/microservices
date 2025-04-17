package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RequestReplacementStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.models.Reactive;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "request_replacement")
public class RequestReplacementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", unique = true, nullable = false, updatable = false)
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "reactive_id", referencedColumnName = "reactive_id", nullable = false)
    private ReactiveEntity reactive;

    @Column(nullable = false, name = "charge_person")
    private Integer chargePerson;

    @Column(name = "request_quantity", nullable = false)
    private Double requestQuantity;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date", nullable = false)
    private LocalDateTime requestDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestReplacementStatus status;
}
