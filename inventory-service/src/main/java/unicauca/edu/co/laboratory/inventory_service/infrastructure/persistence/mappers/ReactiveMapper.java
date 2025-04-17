package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.ReactiveRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ReactiveResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.domain.models.Reactive;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ParentHouseEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;

@Mapper(componentModel = "spring", uses = {ParentHouseMapper.class})
public interface ReactiveMapper {
    @Mapping(source = "house.parentHouseId", target = "house")
    Reactive toDomain(ReactiveEntity entity);

    @Mapping(source = "house", target = "house", qualifiedByName = "toParentHouseEntity")
    ReactiveEntity toEntity(Reactive domain);

    @Mapping(target = "reactiveId", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    Reactive toDomain(ReactiveRequestDTO dto);

    @Mapping(target = "type", expression = "java(domain.getType() != null ? domain.getType().getFormattedName() : null)")
    ReactiveResponseDTO toDTO(Reactive domain);

    @Named("toParentHouseEntity")
    default ParentHouseEntity toParentHouseEntity(Long houseId) {
        if (houseId == null) {
            return null;
        }
        ParentHouseEntity parentHouseEntity = new ParentHouseEntity();
        parentHouseEntity.setParentHouseId(houseId);
        return parentHouseEntity;
    }
}
