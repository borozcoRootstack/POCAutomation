package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelReader {

    private Workbook workbook;

    public ExcelReader(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(file);
    }

    public List<Map<String, String>> getData(String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        System.out.println("Reading sheet: " + sheetName);
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) return data;

        Row headerRow = sheet.getRow(0);
        if (headerRow == null) return data;
        System.out.println("Total rows (including header): " + sheet.getLastRowNum());
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Map<String, String> rowMap = new HashMap<>();
            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                Cell headerCell = headerRow.getCell(j);
                if (headerCell == null) continue;

                String key = headerCell.getStringCellValue().trim();
                String value = getCellValueAsString(row.getCell(j));
                System.out.println("Header column: '" + key + "'");
                rowMap.put(key, value);
            }

            if (!rowMap.isEmpty()) {
                data.add(rowMap);
            }
        }

        return data;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                double value = cell.getNumericCellValue();
                if (value == Math.floor(value)) {
                    return String.valueOf((int) value); // Evita .0
                } else {
                    return String.valueOf(value);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
