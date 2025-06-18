package unicauca.edu.co.laboratory.inventory_service.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.InventoryMovementEntity;
import unicauca.edu.co.laboratory.inventory_service.infrastructure.persistence.entities.ReactiveEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelReportGenerator {
    public static byte[] generateStockStatus(List<ReactiveEntity> reactives) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Stock Status");

            CreationHelper createHelper = workbook.getCreationHelper();

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            setBorders(headerStyle);

            CellStyle dataStyle = workbook.createCellStyle();
            setBorders(dataStyle);

            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd"));
            setBorders(dateStyle);

            CellStyle alertStyle = workbook.createCellStyle();
            alertStyle.cloneStyleFrom(dataStyle);
            alertStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
            alertStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            String[] headers = {"ID", "Nombre", "Cantidad", "Cantidad Mínima", "Unidad", "Fecha Vencimiento", "Estado"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowIdx = 1;
            for (ReactiveEntity r : reactives) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(r.getReactiveId());
                row.createCell(1).setCellValue(r.getName());
                row.createCell(2).setCellValue(r.getQuantity());
                row.createCell(3).setCellValue(r.getMinimumQuantity());
                row.createCell(4).setCellValue(r.getMeasureUnit().name());

                Cell dateCell = row.createCell(5);
                dateCell.setCellValue(r.getSafetySheetExpiration());
                dateCell.setCellStyle(dateStyle);

                Cell statusCell = row.createCell(6);
                statusCell.setCellValue(r.getStatus().name());
                statusCell.setCellStyle(
                        r.getQuantity() < r.getMinimumQuantity() ? alertStyle : dataStyle
                );

                for (int i = 0; i <= 4; i++) {
                    row.getCell(i).setCellStyle(dataStyle);
                }
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error al generar el Excel de stock", e);
        }
    }

    public static byte[] generateUsageReport(List<InventoryMovementEntity> movements) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Movimientos");

            CreationHelper createHelper = workbook.getCreationHelper();

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFont(headerFont);
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            setBorders(headerStyle);

            CellStyle dataStyle = workbook.createCellStyle();
            setBorders(dataStyle);

            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm"));
            setBorders(dateStyle);

            String[] headers = {"ID", "Nombre Reactivo", "Tipo", "Cantidad", "Fecha", "Encargado"};
            Row header = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = header.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            int rowIdx = 1;
            for (InventoryMovementEntity m : movements) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(m.getMovementId());
                row.createCell(1).setCellValue(m.getReactive().getName());
                row.createCell(2).setCellValue(m.getMovementType().name());
                row.createCell(3).setCellValue(m.getQuantity());

                Cell dateCell = row.createCell(4);
                dateCell.setCellValue(m.getMovementDate());
                dateCell.setCellStyle(dateStyle);

                row.createCell(5).setCellValue(m.getChargePerson());

                for (int i = 0; i <= 3; i++) {
                    row.getCell(i).setCellStyle(dataStyle);
                }
                row.getCell(5).setCellStyle(dataStyle);
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            return out.toByteArray();

        } catch (IOException e) {
            throw new RuntimeException("Error al generar el Excel de movimientos", e);
        }
    }

    private static void setBorders(CellStyle style) {
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }
}