package unicauca.edu.co.laboratory.inventory_service.infrastructure.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.InventoryMovementRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.InventoryMovementResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.InventoryMovementPort;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MovementType;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movement")
public class InventoryMovementController {
    private final InventoryMovementPort movementService;

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<List<InventoryMovementResponseDTO>> searchMovements(
            @RequestParam(required = false) MovementType type,
            @RequestParam(required = false) Integer reactiveId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "movementDate") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        List<InventoryMovementResponseDTO> results;

        if (type != null && reactiveId != null) {
            results = movementService.getInventoryMovementsByReactive(reactiveId).stream()
                    .filter(m -> m.getMovementType() == type)
                    .sorted((a, b) -> sortDir.equalsIgnoreCase("asc")
                            ? a.getMovementDate().compareTo(b.getMovementDate())
                            : b.getMovementDate().compareTo(a.getMovementDate()))
                    .skip((long) page * size)
                    .limit(size)
                    .toList();
        } else if (type != null) {
            results = movementService.getInventoryMovementsByType(type).stream()
                    .sorted((a, b) -> sortDir.equalsIgnoreCase("asc")
                            ? a.getMovementDate().compareTo(b.getMovementDate())
                            : b.getMovementDate().compareTo(a.getMovementDate()))
                    .skip((long) page * size)
                    .limit(size)
                    .toList();
        } else if (reactiveId != null) {
            results = movementService.getInventoryMovementsByReactive(reactiveId).stream()
                    .sorted((a, b) -> sortDir.equalsIgnoreCase("asc")
                            ? a.getMovementDate().compareTo(b.getMovementDate())
                            : b.getMovementDate().compareTo(a.getMovementDate()))
                    .skip((long) page * size)
                    .limit(size)
                    .toList();
        } else {
            results = movementService.getInventoryMovements().stream()
                    .sorted((a, b) -> sortDir.equalsIgnoreCase("asc")
                            ? a.getMovementDate().compareTo(b.getMovementDate())
                            : b.getMovementDate().compareTo(a.getMovementDate()))
                    .skip((long) page * size)
                    .limit(size)
                    .toList();
        }

        return ResponseEntity.ok(results);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<List<InventoryMovementResponseDTO>> getAllMovements() {
        return ResponseEntity.ok(movementService.getInventoryMovements());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<InventoryMovementResponseDTO> getMovementById(@PathVariable Integer id) {
        return movementService.getInventoryMovementById(Long.valueOf(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<InventoryMovementResponseDTO> saveMovement(@RequestBody @Valid InventoryMovementRequestDTO dto) {
        InventoryMovementResponseDTO response = movementService.saveInventoryMovement(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> updateMovement(@PathVariable Integer id,
                                               @Valid @RequestBody InventoryMovementRequestDTO request) {
        boolean updated = movementService.updateInventoryMovement(id, request);
        return updated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteMovement(@PathVariable Integer id) {
        movementService.deleteInventoryMovement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{type}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<List<InventoryMovementResponseDTO>> getMovementsByType(@PathVariable MovementType type) {
        return ResponseEntity.ok(movementService.getInventoryMovementsByType(type));
    }

    @GetMapping("/reactive/{reactiveId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<List<InventoryMovementResponseDTO>> getMovementsByReactive(@PathVariable Integer reactiveId) {
        return ResponseEntity.ok(movementService.getInventoryMovementsByReactive(reactiveId));
    }
}