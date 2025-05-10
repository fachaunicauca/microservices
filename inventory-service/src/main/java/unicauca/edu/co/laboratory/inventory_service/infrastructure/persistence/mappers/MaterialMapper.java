package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.MaterialRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.MaterialResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MeasurementUnit;
import unicauca.edu.co.laboratory.inventory_service.domain.models.Material;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.MaterialEntity;

@Mapper(componentModel = "spring")
public interface MaterialMapper {
    Material toDomain(MaterialEntity entity);

    MaterialEntity toEntity(Material domain);

    MaterialResponseDTO toDTO(Material domain);

    @Mapping(target = "materialId", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    Material updateDomain(MaterialEntity entity, @MappingTarget Material domain);

    @Mapping(target = "materialId", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "capacity", expression = "java(combineCapacity(dto.getCapacity(), dto.getMeasureUnit()))")
    Material toDomain(MaterialRequestDTO dto);

    default String combineCapacity(Integer capacity, MeasurementUnit unit) {
        return capacity + " " + unit.name();
    }
}
