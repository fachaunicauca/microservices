package unicauca.edu.co.laboratory.inventory_service.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RequestReplacementStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestReplacementRequestDTO {
    @NotNull(message = "El ID del reactivo es obligatorio")
    private Long reactiveId;

    @NotNull(message = "El ID de la persona responsable es obligatorio")
    private Integer chargePerson;

    @NotNull(message = "La cantidad solicitada es obligatoria")
    @Min(value = 1, message = "La cantidad solicitada debe ser mayor a 0")
    private Double requestQuantity;

    @NotNull(message = "La fecha de solicitud es obligatoria")
    private LocalDateTime requestDate;

    @NotNull(message = "El estado de solicitud es obligatoro")
    private RequestReplacementStatus status;
}
