package unicauca.edu.co.laboratory.inventory_service.infrastructure.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unicauca.edu.co.laboratory.inventory_service.application.services.B2Service;

@RestController
@RequiredArgsConstructor
@RequestMapping("/b2")
public class B2Controller {
    private final B2Service b2Service;

    @GetMapping("/test")
    public ResponseEntity<String> testConnection() {
        boolean connected = b2Service.testConnection();
        return connected ? ResponseEntity.ok("Conexión exitosa con Backblaze B2.")
                : ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("No se pudo conectar con B2.");
    }
}
