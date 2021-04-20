package com.form.heroesBack.export.typesExport;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import com.form.heroesBack.export.interfaces.ExportStrategy;

import org.apache.commons.lang.WordUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExportExcel implements ExportStrategy {

    /**
     * @param listElements
     * @return ByteArrayInputStream
     */
    public <T> ByteArrayInputStream export(List<T> list) {

        try {

            XSSFWorkbook workbook = generateExcel(list);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            out.close();
            workbook.close();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            log.error("Exception error", e);
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    /**
     * @param list
     * @return XSSFWorkbook
     */
    private <T> XSSFWorkbook generateExcel(List<T> list) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        XSSFCellStyle headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setFontName("ARIAL");
        headerFont.setFontHeightInPoints((short) 9);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        headerStyle
                .setFillForegroundColor(new XSSFColor(new java.awt.Color(112, 173, 71), new DefaultIndexedColorMap()));
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());

        XSSFCellStyle bodyStyleEven = workbook.createCellStyle();
        XSSFFont bodyFont = workbook.createFont();
        bodyFont.setFontName("ARIAL");
        bodyFont.setFontHeightInPoints((short) 10);
        bodyStyleEven.setFont(bodyFont);
        bodyStyleEven.setBorderBottom(BorderStyle.THIN);
        bodyStyleEven.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        bodyStyleEven.setBorderLeft(BorderStyle.THIN);
        bodyStyleEven.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        bodyStyleEven.setBorderRight(BorderStyle.THIN);
        bodyStyleEven.setRightBorderColor(IndexedColors.BLACK.getIndex());
        bodyStyleEven.setBorderTop(BorderStyle.THIN);
        bodyStyleEven.setTopBorderColor(IndexedColors.BLACK.getIndex());
        bodyStyleEven
                .setFillForegroundColor(new XSSFColor(new java.awt.Color(217, 217, 217), new DefaultIndexedColorMap()));
        bodyStyleEven.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFCellStyle bodyStyleOdd = workbook.createCellStyle();
        bodyStyleOdd.setFont(bodyFont);
        bodyStyleOdd.setBorderBottom(BorderStyle.THIN);
        bodyStyleOdd.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        bodyStyleOdd.setBorderLeft(BorderStyle.THIN);
        bodyStyleOdd.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        bodyStyleOdd.setBorderRight(BorderStyle.THIN);
        bodyStyleOdd.setRightBorderColor(IndexedColors.BLACK.getIndex());
        bodyStyleOdd.setBorderTop(BorderStyle.THIN);
        bodyStyleOdd.setTopBorderColor(IndexedColors.BLACK.getIndex());

        XSSFRow header = sheet.createRow(0);
        for (int i = 1; i < list.get(0).getClass().getDeclaredFields().length; i++) {
            header.createCell(i - 1)
                    .setCellValue(WordUtils.capitalize(list.get(0).getClass().getDeclaredFields()[i].getName()));
            header.getCell(i - 1).setCellStyle(headerStyle);
            sheet.autoSizeColumn(i);
        }
        list.forEach(c -> {

            Field[] fields = c.getClass().getDeclaredFields();
            XSSFRow row = sheet.createRow(list.indexOf(c) + 1);
            int columnCount = 0;
            for (Field field : fields) {
                if (!"id".equals(field.getName())) {
                    field.setAccessible(true);
                    columnCount = insertCell(bodyStyleEven, bodyStyleOdd, c, row, columnCount, field, list);
                }
            }
        });

        for (int i = 0; i < list.get(0).getClass().getDeclaredFields().length; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

    /**
     * @param bodyStyleEven
     * @param bodyStyleOdd
     * @param c
     * @param row
     * @param columnCount
     * @param field
     * @param list
     * @return int
     */
    private <T> int insertCell(XSSFCellStyle bodyStyleEven, XSSFCellStyle bodyStyleOdd, Object c, XSSFRow row,
            int columnCount, Field field, List<T> list) {
        try {
            if (list.indexOf(c) % 2 == 0) {
                row.createCell(columnCount).setCellValue(field.get(c).toString());
                row.getCell(columnCount++).setCellStyle(bodyStyleEven);
            } else {
                row.createCell(columnCount).setCellValue(field.get(c).toString());
                row.getCell(columnCount++).setCellStyle(bodyStyleOdd);
            }
        } catch (IllegalArgumentException | IllegalAccessException e) {

            log.error(e.getLocalizedMessage(), e);
            e.printStackTrace();
        }
        return columnCount;
    }
}