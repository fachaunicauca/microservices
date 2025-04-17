package unicauca.edu.co.laboratory.inventory_service.application.dto.response;

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
public class InventoryMovementResponseDTO {
    private Long movementId;
    private Long reactiveId;
    private MovementType movementType;
    private Double quantity;
    private String movementDescription;
    private LocalDateTime movementDate;
    private Integer chargePerson;
}
