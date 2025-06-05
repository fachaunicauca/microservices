package unicauca.edu.co.laboratory.inventory_service.infrastructure.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.ReactiveRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ParentHouseResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ReactiveResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.ParentHousePort;
import unicauca.edu.co.laboratory.inventory_service.application.ports.ReactivePort;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.ReactiveType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.RiskType;
import unicauca.edu.co.laboratory.inventory_service.domain.exceptions.AlreadyExistException;
import unicauca.edu.co.laboratory.inventory_service.domain.exceptions.NotFoundException;

import javax.print.attribute.standard.Media;
import java.nio.file.FileSystemException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reactive")
public class ReactiveController {
    private final ReactivePort reactivePort;
    private final ParentHousePort parentHousePort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<List<ReactiveResponseDTO>> getAllReactives() {
        List<ReactiveResponseDTO> reactives = reactivePort.getReactive();
        return ResponseEntity.ok(reactives);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<ReactiveResponseDTO> getReactiveById(@PathVariable(value = "id") Long id) {
        return reactivePort.getReactiveById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Reactive " + id + " not found"));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<ReactiveResponseDTO> createReactive(@Valid @ModelAttribute ReactiveRequestDTO requestDTO) throws FileSystemException {
        Optional<ReactiveResponseDTO> existingReactive = reactivePort.getReactiveByCode(requestDTO.getCode());
        Optional<ParentHouseResponseDTO> existingParentHouse = parentHousePort.getParentHouseById(requestDTO.getHouse());
        if (existingParentHouse.isEmpty()) {
            throw new NotFoundException("Parent house " + requestDTO.getHouse() + " not found");
        }
        List<ReactiveResponseDTO> reactives = reactivePort.getReactive();
        if (existingReactive.isPresent()) {
            throw new AlreadyExistException("Reactive with code " + requestDTO.getCode() + " already exists");
        }
        if (reactives.stream().anyMatch(reactive -> reactive.getName().equalsIgnoreCase(requestDTO.getName()) || reactive.getFormula().equalsIgnoreCase(requestDTO.getFormula()))) {
            throw new AlreadyExistException("Reactive with name " + requestDTO.getName() + " or formula " + requestDTO.getFormula() + " already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reactivePort.saveReactive(requestDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<Boolean> updateReactive(@PathVariable(value = "id") Long id, @Valid @ModelAttribute ReactiveRequestDTO requestDTO) throws FileSystemException {
        Optional<ReactiveResponseDTO> existingReactive = reactivePort.getReactiveById(id);
        Optional<ParentHouseResponseDTO> existingParentHouse = parentHousePort.getParentHouseById(requestDTO.getHouse());
        if (existingReactive.isEmpty()) {
            throw new NotFoundException("Reactive " + id + " not found");
        }
        if (existingParentHouse.isEmpty()) {
            throw new NotFoundException("Parent house " + requestDTO.getHouse() + " not found");
        }

        Optional<ReactiveResponseDTO> reactiveWithCode = reactivePort.getReactiveByCode(requestDTO.getCode());
        if (reactiveWithCode.isPresent() && !reactiveWithCode.get().getReactiveId().equals(id)) {
            throw new AlreadyExistException("Reactive with code " + requestDTO.getCode() + " already exists");
        }

        List<ReactiveResponseDTO> reactives = reactivePort.getReactive();
        if (reactives.stream().anyMatch(reactive -> (reactive.getName().equalsIgnoreCase(requestDTO.getName()) || reactive.getFormula().equalsIgnoreCase(requestDTO.getFormula())) && !reactive.getReactiveId().equals(id))) {
            throw new AlreadyExistException("Reactive with name " + requestDTO.getName() + " or formula " + requestDTO.getFormula() + " already exists");
        }

        boolean updated = reactivePort.updateReactive(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<Boolean> deleteReactive(@PathVariable("id") Long id) {
        Optional<ReactiveResponseDTO> existingReactive = reactivePort.getReactiveById(id);
        if (existingReactive.isEmpty()) {
            throw new NotFoundException("Reactive " + id + " not found");
        }
        boolean deleted = reactivePort.deleteReactive(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<List<ReactiveResponseDTO>> getReactiveByName(@PathVariable(value = "name") String name) {
        List<ReactiveResponseDTO> reactives = reactivePort.getReactiveByName(name);
        if (reactives.isEmpty()) {
            throw new NotFoundException("Reactive with name " + name + " not found");
        }
        return ResponseEntity.ok(reactives);
    }

    @GetMapping("/code/{code}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<ReactiveResponseDTO> getReactiveByCode(@PathVariable(value = "code") String code) {
        return reactivePort.getReactiveByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Reactive with code " + code + " not found"));
    }

    @GetMapping("/type/{typeStr}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<List<ReactiveResponseDTO>> getReactiveByType(@PathVariable(value = "typeStr") String typeStr) {
        ReactiveType type;
        try {
            type = ReactiveType.valueOf(typeStr);
        } catch (IllegalArgumentException e) {
            type = ReactiveType.findByFormattedName(typeStr);
            if (type == null) {
                throw new NotFoundException("Invalid reactive type: " + typeStr);
            }
        }

        List<ReactiveResponseDTO> reactives = reactivePort.getReactiveByType(type);
        if (reactives.isEmpty()) {
            throw new NotFoundException("No reactives found with type " + type);
        }
        return ResponseEntity.ok(reactives);
    }

    @GetMapping("/risk/{risk}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<List<ReactiveResponseDTO>> getReactiveByRiskType(@PathVariable(value = "risk") String risk) {
        RiskType riskType;
        try {
            riskType = RiskType.fromString(risk);
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Invalid risk type: " + risk);
        }

        List<ReactiveResponseDTO> reactives = reactivePort.getReactiveByRiskType(riskType);
        if (reactives.isEmpty()) {
            throw new NotFoundException("No reactives found with risk type " + riskType);
        }
        return ResponseEntity.ok(reactives);
    }

    @GetMapping("/parent/{parentId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<List<ReactiveResponseDTO>> getReactiveByParent(@PathVariable(value = "parentId") Long parentId) {
        Optional<ParentHouseResponseDTO> existingParentHouse = parentHousePort.getParentHouseById(parentId);
        if (existingParentHouse.isEmpty()) {
            throw new NotFoundException("Parent house " + parentId + " not found");
        }
        List<ReactiveResponseDTO> reactives = reactivePort.getReactiveByParent(parentId);
        if (reactives.isEmpty()) {
            throw new NotFoundException("No reactives found with parent ID " + parentId);
        }
        return ResponseEntity.ok(reactives);
    }

    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<List<ReactiveResponseDTO>> getReactiveByStatus(@PathVariable(value = "status") ReactiveStatus status) {
        List<ReactiveResponseDTO> reactives = reactivePort.getReactiveByStatus(status);
        if (reactives.isEmpty()) {
            throw new NotFoundException("No reactives found with status " + status);
        }
        return ResponseEntity.ok(reactives);
    }
}
