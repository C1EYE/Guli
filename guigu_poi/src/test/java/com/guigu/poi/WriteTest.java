package com.guigu.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class WriteTest {
    @Test
    public void test1() {
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet("会员管理");
        Row row = sheet.createRow(0);
        Cell cell1 = row.createCell(0);
        cell1.setCellValue("人数");
        Cell cell2 = row.createCell(1);
        cell2.setCellValue(12345);

        try (FileOutputStream fos = new FileOutputStream("./1.xls")) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("用户");
        for (int i = 0; i < 65535; i++) {
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(i + "SB");
        }
        try (FileOutputStream fos = new FileOutputStream("./1.xls")) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
