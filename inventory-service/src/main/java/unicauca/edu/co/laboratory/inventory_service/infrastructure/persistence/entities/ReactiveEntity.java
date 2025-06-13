package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MeasurementUnit;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RiskType;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "reactive")
public class ReactiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reactive_id", unique = true, nullable = false, updatable = false)
    private Long reactiveId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactiveType type;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false, referencedColumnName = "parent_house_id")
    private ParentHouseEntity house;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String formula;

    @Column(unique = true, nullable = false, length = 100)
    private String code;

    @Column(nullable = false)
    private Double quantity;

    @Column(name = "minimum_quantity", nullable = false)
    private Double minimumQuantity;

    @Column(name = "measure_unit", nullable = false)
    @Enumerated(EnumType.STRING)
    private MeasurementUnit measureUnit;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactiveStatus status;

    @Column(name = "safety_sheet", nullable = false, length = 300)
    private String safetySheet;

    @Column(name = "safety_sheet_update", nullable = false)
    private LocalDateTime safetySheetUpdate;

    @Column(name = "safety_sheet_expiration", nullable = false)
    private LocalDateTime safetySheetExpiration;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "risk_type_reactive",
            joinColumns = @JoinColumn (name = "reactive_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "risk_types")
    private Set<RiskType> riskTypes;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ReactiveEntity that = (ReactiveEntity) o;
        return getReactiveId() != null && Objects.equals(getReactiveId(), that.getReactiveId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}