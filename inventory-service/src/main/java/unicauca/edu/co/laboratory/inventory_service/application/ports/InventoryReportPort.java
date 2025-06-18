package unicauca.edu.co.laboratory.inventory_service.application.ports;

import java.time.LocalDate;

public interface InventoryReportPort {
    byte[] generateStockStatusReport();
    byte[] generateInventoryUsageReport(LocalDate startDate, LocalDate endDate);
}
