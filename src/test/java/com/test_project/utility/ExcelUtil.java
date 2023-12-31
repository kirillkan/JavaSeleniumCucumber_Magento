package com.test_project.utility;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This is a utility for reading from writing to excel files.
 * it works with xls and xlsx files.
 */
public class ExcelUtil {

    private Sheet workSheet;
    private Workbook workBook;
    private String path;

    public ExcelUtil(String path, String sheetName) {
        this.path = path;
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            workBook = WorkbookFactory.create(ExcelFile);
            workSheet = workBook.getSheet(sheetName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, String>> getDataList() {
        List<String> columns = getColumnsNames();
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            Row row = workSheet.getRow(i);
            Map<String, String> rowMap = new HashMap<String, String>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }
            data.add(rowMap);
        }
        return data;
    }

    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();

        for (Cell cell : workSheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    public int rowCount() {
        return workSheet.getLastRowNum()+1;
    }

}
