package unicauca.edu.co.laboratory.inventory_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.ParentHouseRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ParentHouseResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.ParentHousePort;
import unicauca.edu.co.laboratory.inventory_service.domain.models.ParentHouse;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ParentHouseEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.mappers.ParentHouseMapper;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.ParentHouseRepositoryJPA;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParenHouseService implements ParentHousePort {
    private final ParentHouseRepositoryJPA parentHouseRepositoryJPA;
    private final ParentHouseMapper parentHouseMapper;

    @Override
    public List<ParentHouseResponseDTO> getParentHouseS() {
        return parentHouseRepositoryJPA.findAll().stream()
                .map(parentHouseMapper::toDomain)
                .map(parentHouseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ParentHouseResponseDTO> getParentHouseById(Long id) {
        return parentHouseRepositoryJPA.findById(id)
                .map(parentHouseMapper::toDomain)
                .map(parentHouseMapper::toDTO);
    }

    @Override
    public ParentHouseResponseDTO saveParentHouse(ParentHouseRequestDTO parentHouseRequestDTO) {
        ParentHouse parentHouse = parentHouseMapper.toDomain(parentHouseRequestDTO);
        ParentHouseEntity parentHouseEntity = parentHouseMapper.toEntity(parentHouse);
        return parentHouseMapper.toDTO(
                parentHouseMapper.toDomain(
                        parentHouseRepositoryJPA.save(parentHouseEntity)));
    }

    @Override
    public boolean updateParentHouse(Long id, ParentHouseRequestDTO parentHouseRequestDTO) {
        Optional<ParentHouseEntity> parentHouseEntity = parentHouseRepositoryJPA.findById(id);
        if (parentHouseEntity.isPresent()) {
            ParentHouseEntity updatedEntity = parentHouseEntity.orElseThrow(() -> new RuntimeException("Parent house not found"));
            updatedEntity.setName(parentHouseRequestDTO.getName());
            parentHouseRepositoryJPA.save(updatedEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteParentHouse(Long id) {
        if (parentHouseRepositoryJPA.existsById(id)) {
            parentHouseRepositoryJPA.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ParentHouseResponseDTO> getParentHouseByName(String name) {
        return parentHouseRepositoryJPA.findByName(name).stream()
                .map(parentHouseMapper::toDomain)
                .map(parentHouseMapper::toDTO)
                .collect(Collectors.toList());
    }
}
