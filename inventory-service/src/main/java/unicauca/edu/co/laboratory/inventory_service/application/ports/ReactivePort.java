package unicauca.edu.co.laboratory.inventory_service.application.ports;

import unicauca.edu.co.laboratory.inventory_service.application.dto.request.ReactiveRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ReactiveResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RiskType;

import java.nio.file.FileSystemException;
import java.util.List;
import java.util.Optional;

public interface ReactivePort {
    List<ReactiveResponseDTO> getReactive();
    Optional<ReactiveResponseDTO> getReactiveById(Long reactiveId);
    ReactiveResponseDTO saveReactive(ReactiveRequestDTO reactive) throws FileSystemException;
    boolean deleteReactive(Long reactiveId);
    boolean updateReactive(Long id, ReactiveRequestDTO reactive) throws FileSystemException;
    List<ReactiveResponseDTO> getReactiveByStatus(ReactiveStatus status);
    List<ReactiveResponseDTO> getReactiveByName(String name);
    List<ReactiveResponseDTO> getReactiveByRiskType(RiskType risk);
    Optional<ReactiveResponseDTO> getReactiveByCode(String code);
    List<ReactiveResponseDTO> getReactiveByType(ReactiveType type);
    List<ReactiveResponseDTO> getReactiveByParent(Long parentId);
}
