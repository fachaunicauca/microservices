package unicauca.edu.co.laboratory.inventory_service.infrastructure.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import unicauca.edu.co.laboratory.inventory_service.application.dto.request.ParentHouseRequestDTO;
import unicauca.edu.co.laboratory.inventory_service.application.dto.response.ParentHouseResponseDTO;
import unicauca.edu.co.laboratory.inventory_service.application.ports.ParentHousePort;
import unicauca.edu.co.laboratory.inventory_service.domain.exceptions.AlreadyExistException;
import unicauca.edu.co.laboratory.inventory_service.domain.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parent-house")
public class ParentHouseController {
    private final ParentHousePort parentHousePort;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<List<ParentHouseResponseDTO>> getAllParentHouses() {
        List<ParentHouseResponseDTO> parentHouseResponseDTOList = parentHousePort.getParentHouseS();
        return ResponseEntity.ok(parentHouseResponseDTOList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<ParentHouseResponseDTO> getParentHouseById(@PathVariable(value = "id") Long id) {
        return parentHousePort.getParentHouseById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new NotFoundException("Parent house " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<ParentHouseResponseDTO> createParentHouse(@Valid @RequestBody ParentHouseRequestDTO requestDTO) {
        if (!parentHousePort.getParentHouseByName(requestDTO.getName()).isEmpty()){
            throw new AlreadyExistException("Parent house " + requestDTO.getName() + " already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(parentHousePort.saveParentHouse(requestDTO));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<Boolean> updateParentHouse(@PathVariable(value = "id") Long id, @Valid @RequestBody ParentHouseRequestDTO requestDTO) {
        Optional<ParentHouseResponseDTO> existingParentHouse = parentHousePort.getParentHouseById(id);
        if (existingParentHouse.isEmpty()) {
            throw new NotFoundException("Parent house " + id + " not found");
        } else if (!parentHousePort.getParentHouseByName(requestDTO.getName()).isEmpty()) {
            throw new AlreadyExistException("Parent house " + requestDTO.getName() + " already exists");
        }
        boolean updated = parentHousePort.updateParentHouse(id, requestDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<Boolean> deleteParentHouse(@PathVariable(value = "id") Long id) {
        Optional<ParentHouseResponseDTO> existingParentHouse = parentHousePort.getParentHouseById(id);
        if (existingParentHouse.isEmpty()) {
            throw new NotFoundException("Parent house " + id + " not found");
        }
        boolean deleted = parentHousePort.deleteParentHouse(id);
        return ResponseEntity.ok(deleted);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER', 'ROLE_TEACHER')")
    public ResponseEntity<List<ParentHouseResponseDTO>> getParentHouseByName(@PathVariable(value = "name") String name) {
        List<ParentHouseResponseDTO> parentHouseResponseDTOList = parentHousePort.getParentHouseByName(name);
        if (parentHouseResponseDTOList.isEmpty()) {
            throw new NotFoundException("Parent house with name " + name + " not found");
        }
        return ResponseEntity.ok(parentHouseResponseDTOList);
    }
}
