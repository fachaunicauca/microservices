package unicauca.edu.co.laboratory.inventory_service.domain.models;

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
public class RequestReplacement {
    private Long requestId;
    private Long reactiveId;
    private Integer chargePerson;
    private Double requestQuantity;
    private LocalDateTime requestDate;
    private RequestReplacementStatus status;

    public boolean isPending() {
        return status == RequestReplacementStatus.PENDIENTE;
    }
}
