package com.unicauca.sga.educatorService.service;

import com.unicauca.sga.educatorService.infrastructure.controller.DTO.CreateUserDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelUserParser {
    public List<CreateUserDTO> parse(MultipartFile file) throws IOException {
        List<CreateUserDTO> users = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Ignora la fila de encabezados
            Row row = sheet.getRow(i);
            if (row == null) continue;

            CreateUserDTO user = new CreateUserDTO(
                    row.getCell(0).getStringCellValue(), // username
                    row.getCell(1).getStringCellValue(), // email
                    row.getCell(2).getStringCellValue(), // firstName
                    row.getCell(3).getStringCellValue(), // lastName
                    row.getCell(4).getStringCellValue()  // password
            );
            users.add(user);
        }

        workbook.close();
        return users;
    }
}
