package unicauca.edu.co.laboratory.inventory_service.infrastructure.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import unicauca.edu.co.laboratory.inventory_service.application.services.CloudinaryService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class CloudinaryFileController {
    private final CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String url = cloudinaryService.uploadFile(file);
            return ResponseEntity.ok(url);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir archivo");
        }
    }

    @GetMapping("/ping")
    public ResponseEntity<String> testConnection() {
        boolean isConnected = cloudinaryService.testConnection();
        if (isConnected) {
            return ResponseEntity.ok("Conexión con Cloudinary exitosa");
        } else {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Fallo al conectar con Cloudinary");
        }
    }
}
