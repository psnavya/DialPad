package com.test.dto.utilities;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHandler {

    private Sheet excelSheetRetriever(String sheetName) throws IOException {        //variable for storing the cell value
        Workbook wb = null;           //initialize Workbook null
//reading data from a file in the form of bytes
        FileInputStream fis = new FileInputStream("src/test/resources/TelephoneDial.xlsx");
//constructs an XSSFWorkbook object, by buffering the whole stream into the memory
        wb = new XSSFWorkbook(fis);
        return wb.getSheet(sheetName);
    }

    public String readCellData(String sheetName, int vRow, int vColumn) throws IOException {
        String value = null;          //variable for storing the cell value
        Sheet sheet = excelSheetRetriever(sheetName);  //getting the XSSFSheet object at given index
        Row row = sheet.getRow(vRow); //returns the logical row
        Cell cell = row.getCell(vColumn); //getting the cell representing the given column
        value = cell.getStringCellValue();//getting cell value
        return value;               //returns the cell value
    }

    public int[] getRowRange(String sheetName) throws IOException {

        int[] rowRange = new int[2];
        Sheet sheet = excelSheetRetriever(sheetName);
        rowRange[0] = 1;
        rowRange[1] = sheet.getLastRowNum();
        return rowRange;

    }

    public List<String> getInputList(String sheetName, int[] rowRange) throws IOException {
        List<String> testDataList = new ArrayList<>();
        for (int i = rowRange[0]; i <= rowRange[1]; i++)
            testDataList.add(readCellData(sheetName, i, 1));
        return testDataList;
    }

    public LinkedList<String> getExpectedValues(String sheetName, String inputValue, int[] rowRange) throws IOException {

        LinkedList<String> expectedCombinationList;
        String expectedCellValue = null;
        for (int i = rowRange[0]; i <= rowRange[1]; i++) {
            if (readCellData(sheetName, i, 1).equalsIgnoreCase(inputValue))
                expectedCellValue = readCellData(sheetName, i, 2);
        }
        String[] expectedValueArray = expectedCellValue.split(",");
        // Convert array to list
        List<String> expectedList = Arrays.asList(expectedValueArray);
        expectedCombinationList = new LinkedList<>(expectedList);
        return expectedCombinationList;
    }
}




