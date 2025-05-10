package unicauca.edu.co.laboratory.inventory_service.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MeasurementUnit;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.StoragePlaces;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialRequestDTO {
    @NotBlank(message = "El nombre de material es obligatorio")
    private String name;

    @NotBlank(message = "El tipo de material es obligatorio")
    private MaterialType type;

    @NotBlank(message = "La capacidad es obligatoria")
    @Min(value = 0, message = "La capacidad no puede ser negativa")
    private Integer capacity;

    @NotBlank(message = "La unidad de medida es obligatoria")
    private MeasurementUnit measureUnit;

    @NotBlank(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private Integer quantity;

    @NotBlank(message = "El lugar de almacenamiento es obligatorio")
    private StoragePlaces storagePlace;

    @NotBlank(message = "El estado del material es obligatorio")
    private MaterialStatus status;

    private String observation;
}
