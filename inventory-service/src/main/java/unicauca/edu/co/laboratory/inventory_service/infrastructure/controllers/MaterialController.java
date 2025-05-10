package unicauca.edu.co.laboratory.inventory_service.infrastructure.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.MaterialRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.MaterialResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.MaterialPort;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialStatus;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.MaterialType;
import unicauca.edu.co.laboratory.inventory_service.domain.enums.StoragePlaces;
import unicauca.edu.co.laboratory.inventory_service.domain.exceptions.NotFoundException;
import unicauca.edu.co.laboratory.inventory_service.domain.exceptions.AlreadyExistException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/material")
public class MaterialController {
    private final MaterialPort materialPort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MaterialResponseDTO>> getAllMaterials() {
        List<MaterialResponseDTO> materials = materialPort.getMaterials();
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MaterialResponseDTO> getMaterialById(@PathVariable Long id) {
        return materialPort.getMaterialById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Material " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MaterialResponseDTO> createMaterial(@RequestBody MaterialRequestDTO material) {
        String formattedCapacity = material.getCapacity() + " " +material.getMeasureUnit().name();
        List<MaterialResponseDTO> existingMaterials = materialPort.getMaterialByName(material.getName());

        boolean existsSameCapacity = existingMaterials.stream()
                .anyMatch(existing ->
                        existing.getCapacity().equalsIgnoreCase(formattedCapacity)
                );
        if (existsSameCapacity) {
            throw new AlreadyExistException(
                    "Ya existe un material con el nombre '" + material.getName()
                            + "' y la misma capacidad: " + formattedCapacity
            );
        }
        MaterialResponseDTO createdMaterial = materialPort.saveMaterial(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMaterial);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<MaterialResponseDTO> updateMaterial(@PathVariable Long id, @RequestBody MaterialRequestDTO material) {
        if (!materialPort.updateMaterial(id, material)) {
            throw new NotFoundException("Material " + id + " not found");
        }

        return ResponseEntity.ok(materialPort.getMaterialById(id).orElseThrow(() -> new NotFoundException("Material " + id + " not found")));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        if (!materialPort.deleteMaterial(id)) {
            throw new NotFoundException("Material " + id + " not found");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MaterialResponseDTO>> getMaterialByName(@PathVariable String name) {
        List<MaterialResponseDTO> materials = materialPort.getMaterialByName(name);
        if (materials.isEmpty()) {
            throw new NotFoundException("No materials found with name: " + name);
        }
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MaterialResponseDTO>> getMaterialByType(@PathVariable String type) {
        List<MaterialResponseDTO> materials = materialPort.getMaterialByType(MaterialType.valueOf(type));
        if (materials.isEmpty()) {
            throw new NotFoundException("No materials found with type: " + type);
        }
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/storagePlace/{place}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MaterialResponseDTO>> getMaterialByStoragePlace(@PathVariable String place) {
        List<MaterialResponseDTO> materials = materialPort.getMaterialByStoragePlace(StoragePlaces.valueOf(place));
        if (materials.isEmpty()) {
            throw new NotFoundException("No materials found with storage place: " + place);
        }
        return ResponseEntity.ok(materials);
    }

    @GetMapping("/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<MaterialResponseDTO>> getMaterialByStatus(@PathVariable String status) {
        List<MaterialResponseDTO> materials = materialPort.getMaterialByStatus(MaterialStatus.valueOf(status));
        if (materials.isEmpty()) {
            throw new NotFoundException("No materials found with status: " + status);
        }
        return ResponseEntity.ok(materials);
    }
}
