package unicauca.edu.co.laboratory.inventory_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.RequestReplacementRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.RequestReplacementResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.RequestReplacementPort;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.RequestReplacementRepositoryJPA;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestReplacementService implements RequestReplacementPort {

    private final RequestReplacementRepositoryJPA requestReplacementRepository;

    @Override
    public List<RequestReplacementResponseDTO> getReplacements() {
        return List.of();
    }

    @Override
    public Optional<RequestReplacementResponseDTO> getRequestReplacementById(Integer requestId) {
        return Optional.empty();
    }

    @Override
    public RequestReplacementResponseDTO saveRequestReplacement(Long id, RequestReplacementRequestDTO requestReplacement) {
        return null;
    }

    @Override
    public boolean deleteRequestReplacement(Integer requestId) {
        return false;
    }

    @Override
    public boolean updateRequestReplacement(RequestReplacementRequestDTO requestReplacement) {
        return false;
    }

    @Override
    public List<RequestReplacementResponseDTO> getRequestReplacementByStatus(String status) {
        return List.of();
    }

    @Override
    public List<RequestReplacementResponseDTO> getRequestReplacementByReactive(Integer reactiveId) {
        return List.of();
    }
}
