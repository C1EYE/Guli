package com.guigu.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadTest {

    @Test
    public void test() {


        Workbook workbook = null;
        try (FileInputStream fis = new FileInputStream("./1.xls");) {
            workbook = new HSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            String value = cell.getStringCellValue();
            System.out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
