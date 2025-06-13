package unicauca.edu.co.laboratory.inventory_service.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
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
public class ReactiveRequestDTO {
    @NotNull(message = "El tipo de reactivo es obligatorio")
    private ReactiveType type;

    @NotNull(message = "La casa matriz es obligatoria")
    private Long house;

    @NotBlank(message = "El nombre del reactivo es obligatorio")
    private String name;

    @NotBlank(message = "La fórmula es obligatoria")
    private String formula;

    @NotBlank(message = "El código es obligatorio")
    private String code;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Double quantity;

    @NotNull(message = "La cantidad mínima es obligatoria")
    private Double minimumQuantity;

    @NotNull(message = "La unidad de medida es obligatoria")
    private MeasurementUnit measureUnit;

    @NotNull(message = "El estado del reactivo es obligatorio")
    private ReactiveStatus status;

    @NotNull(message = "La ficha de seguridad es obligatoria")
    private MultipartFile safetySheet;

    @NotNull(message = "La fecha de expiracion es obligatoria")
    private LocalDateTime safetySheetExpiration;

    private Set<RiskType> riskTypes;
}
