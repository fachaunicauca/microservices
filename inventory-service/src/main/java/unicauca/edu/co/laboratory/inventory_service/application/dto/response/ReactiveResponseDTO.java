package unicauca.edu.co.laboratory.inventory_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MeasurementUnit;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RiskType;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReactiveResponseDTO {
    private Long reactiveId;
    private String type;
    private Long house;
    private String name;
    private String formula;
    private String code;
    private Double quantity;
    private Double minimumQuantity;
    private MeasurementUnit measureUnit;
    private ReactiveStatus status;
    private String safetySheet;
    private LocalDateTime safetySheetUpdate;
    private LocalDateTime safetySheetExpiration;
    private Set<RiskType> riskTypes;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
