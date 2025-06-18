package unicauca.edu.co.laboratory.inventory_service.application.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import unicauca.edu.co.laboratory.inventory_service.application.ports.InventoryReportPort;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.InventoryMovementEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.InventoryMovementRepositoryJPA;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.repositories.ReactiveRepositoryJPA;
import unicauca.edu.co.laboratory.inventory_service.util.ExcelReportGenerator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryReportService implements InventoryReportPort {

    private final ReactiveRepositoryJPA reactiveRepo;
    private final InventoryMovementRepositoryJPA movementRepo;

    @Override
    public byte[] generateStockStatusReport() {
        List<ReactiveEntity> reactives = reactiveRepo.findAll();

        return ExcelReportGenerator.generateStockStatus(reactives);
    }

    @Override
    public byte[] generateInventoryUsageReport(LocalDate startDate, LocalDate endDate) {
        List<InventoryMovementEntity> movements = movementRepo.findByMovementDateBetween(
                startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));

        return ExcelReportGenerator.generateUsageReport(movements);
    }
}
