package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.ParentHouseRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ParentHouseResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.domain.models.ParentHouse;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ParentHouseEntity;

@Mapper(componentModel = "spring")
public interface ParentHouseMapper {
    ParentHouse toDomain(ParentHouseEntity entity);

    ParentHouseEntity toEntity(ParentHouse domain);

    @Mapping(target = "parentHouseId", ignore = true)
    ParentHouse toDomain(ParentHouseRequestDTO requestDTO);

    ParentHouseResponseDTO toDTO(ParentHouse domain);
}
