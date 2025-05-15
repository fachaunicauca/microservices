package unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.RequestReplacementRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.RequestReplacementResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.domain.models.RequestReplacement;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.RequestReplacementEntity;

@Component
public class RequestReplacementMapper {
    public static RequestReplacement toDomain(RequestReplacementEntity entity){
        return new RequestReplacement(
                entity.getRequestId(),
                entity.getReactive().getReactiveId(),
                entity.getChargePerson(),
                entity.getRequestQuantity(),
                entity.getRequestDate(),
                entity.getStatus()
        );
    }

    public static RequestReplacementEntity toEntity(RequestReplacement domain){
        return new RequestReplacementEntity(
                domain.getRequestId(),
                new ReactiveEntity(domain.getReactiveId(), null, null, null, null, null, null,null, null, null, null, null, null, null, null, null),
                domain.getChargePerson(),
                domain.getRequestQuantity(),
                domain.getRequestDate(),
                domain.getStatus()
        );
    }

    public static RequestReplacement toDomain(RequestReplacementRequestDTO dto){
        return new RequestReplacement(
                null,
                dto.getReactiveId(),
                dto.getChargePerson(),
                dto.getRequestQuantity(),
                dto.getRequestDate(),
                dto.getStatus()
        );
    }

    public static RequestReplacementResponseDTO toDTO(RequestReplacement domain){
        return new RequestReplacementResponseDTO(
                domain.getRequestId(),
                domain.getReactiveId(),
                domain.getChargePerson(),
                domain.getRequestQuantity(),
                domain.getRequestDate(),
                domain.getStatus()
        );
    }
}
