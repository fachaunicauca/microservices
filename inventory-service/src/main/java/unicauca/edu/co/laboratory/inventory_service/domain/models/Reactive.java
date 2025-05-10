package unicauca.edu.co.laboratory.inventory_service.domain.models;

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
public class Reactive {
    private Long reactiveId;
    private ReactiveType type;
    private Long house;
    private String name;
    private String formula;
    private String code;
    private Double quantity;
    private Double minimumQuantity;
    private MeasurementUnit measureUnit;
    private ReactiveStatus status;
    private LocalDateTime safetySheetUpdate;
    private String safetySheet;
    private Set<RiskType> riskTypes;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public boolean isBelowMinimum(){
        return quantity < minimumQuantity;
    }
}
