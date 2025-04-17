package unicauca.edu.co.laboratory.inventory_service.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentHouseRequestDTO {
    @NotBlank(message = "El nombre de la casa matriz es obligatorio")
    private String name;
}
