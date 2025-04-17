package unicauca.edu.co.laboratory.inventory_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentHouse {
    private Long parentHouseId;
    private String name;
}
