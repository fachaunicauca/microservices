package unicauca.edu.co.laboratory.inventory_service.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class InventoryMovementRequestDTO {
    @NotNull(message = "El ID del reactivo es obligatorio")
    private Long reactiveId;

    @NotNull(message = "El tipo de movimiento es obligatorio")
    private MovementType movementType;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Double quantity;

    @NotBlank(message = "La descripción del movimiento es obligatoria")
    private String movementDescription;

    @NotBlank(message = "La fecha de movimiento es obligatoria")
    private LocalDateTime movementDate;

    @NotNull(message = "El ID de la persona responsable es obligatorio")
    private Integer chargePerson;
}
