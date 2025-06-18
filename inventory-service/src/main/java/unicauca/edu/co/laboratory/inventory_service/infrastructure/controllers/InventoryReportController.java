package unicauca.edu.co.laboratory.inventory_service.infrastructure.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import unicauca.edu.co.laboratory.inventory_service.application.ports.InventoryReportPort;

import java.time.LocalDate;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class InventoryReportController {

    private final InventoryReportPort reportService;

    @GetMapping("/stock")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<byte[]> downloadStockReport() {
        byte[] content = reportService.generateStockStatusReport();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=stock-report.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(content);
    }

    @GetMapping("/usage")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LABORATORY_WORKER')")
    public ResponseEntity<byte[]> downloadUsageReport(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        byte[] content = reportService.generateInventoryUsageReport(startDate, endDate);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=usage-report.xlsx")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(content);
    }
}
