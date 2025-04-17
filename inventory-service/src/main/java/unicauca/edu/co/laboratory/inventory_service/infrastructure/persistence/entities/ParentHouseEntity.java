package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "parent_house")
public class ParentHouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_house_id", unique = true, nullable = false, updatable = false)
    private Long parentHouseId;

    @Column(nullable = false, length = 255)
    private String name;
}
