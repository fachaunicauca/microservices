package unicauca.edu.co.laboratory.inventory_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.StoragePlaces;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Material {
    private Long materialId;
    private String name;
    private MaterialType type;
    private String capacity;
    private Integer quantity;
    private StoragePlaces storagePlace;
    private MaterialStatus status;
    private String observation;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
