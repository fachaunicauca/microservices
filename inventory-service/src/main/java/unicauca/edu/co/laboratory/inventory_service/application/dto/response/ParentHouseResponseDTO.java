package unicauca.edu.co.laboratory.inventory_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentHouseResponseDTO {
    private Long parentHouseId;
    private String name;
}
