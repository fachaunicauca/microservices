package unicauca.edu.co.laboratory.inventory_service.application.ports;

import unicauca.edu.co.laboratory.inventory_service.application.dto.request.RequestReplacementRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.RequestReplacementResponseDTO;

import java.util.List;
import java.util.Optional;

public interface RequestReplacementPort {
    List<RequestReplacementResponseDTO> getReplacements();
    Optional<RequestReplacementResponseDTO> getRequestReplacementById(Integer requestId);
    RequestReplacementResponseDTO saveRequestReplacement(Long id,RequestReplacementRequestDTO requestReplacement);
    boolean deleteRequestReplacement(Integer requestId);
    boolean updateRequestReplacement(RequestReplacementRequestDTO requestReplacement);
    List<RequestReplacementResponseDTO> getRequestReplacementByStatus(String status);
    List<RequestReplacementResponseDTO> getRequestReplacementByReactive(Integer reactiveId);
}
