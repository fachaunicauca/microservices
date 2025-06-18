package unicauca.edu.co.laboratory.inventory_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MovementType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryMovement {
    private Long movementId;
    private Long reactiveId;
    private MovementType movementType;
    private Double quantity;
    private String movementDescription;
    private LocalDateTime movementDate;
    private String chargePerson;
}
