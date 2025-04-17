package unicauca.edu.co.laboratory.inventory_service.application.ports;

import unicauca.edu.co.laboratory.inventory_service.application.dto.request.ParentHouseRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ParentHouseResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ParentHousePort {
    List<ParentHouseResponseDTO> getParentHouseS();
    Optional<ParentHouseResponseDTO> getParentHouseById(Long id);
    ParentHouseResponseDTO saveParentHouse(ParentHouseRequestDTO parentHouseResponseDTO);
    boolean updateParentHouse(Long id, ParentHouseRequestDTO parentHouseResponseDTO);
    boolean deleteParentHouse(Long id);
    List<ParentHouseResponseDTO> getParentHouseByName(String name);
}
