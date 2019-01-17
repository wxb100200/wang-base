package com.base.wang.util;


import com.base.wang.convert.DefaultConvert;
import com.base.wang.convert.ModelConvert;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 */
public class ExcelUtil {
    //导出只有一个sheet的Excel
    public static File export( String sheetName,List<String> titleList,List<String> filedList,
                                 List<ModelConvert> convertList,List objectList){
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        sheetDataHandle(workbook, sheetName,titleList,filedList,convertList,objectList);

        try {
            File tmpFile=File.createTempFile("temp",".xls");
            tmpFile.deleteOnExit();//程序退出时删除临时文件
            FileOutputStream fos=new FileOutputStream(tmpFile);
            workbook.write(fos);
            workbook.close();
            fos.close();
            return tmpFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    //导出有多个sheet的Excel


    //导出
    public static String export(String title,
                                List<String> titleList,
                                List<String> filedList,
                                List<ModelConvert> convertList,
                                List objectList,
                                String dir) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        HSSFCellStyle style3 = workbook.createCellStyle();
        style3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体

        // 把字体应用到当前的样式
        style3.setFont(font2);

        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLACK.index);
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);



      /*  // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("飞扬科技");*/

        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        for (short i = 0; i < titleList.size(); i++) {
            cell= row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(titleList.get(i));
            cell.setCellValue(text);
        }

        //把每个单元格赋值
        int index = 0;
        for (Object object : objectList) {
            index++;
            row = sheet.createRow(index);
            for (int i = 0; i < filedList.size(); i++) {
                try {
                    cell = row.createCell(i);
                    if (index % 2 == 1) {
                        cell.setCellStyle(style2);
                    } else {
                        cell.setCellStyle(style3);
                    }

                    String getMethodName = "get" + filedList.get(i);
                    Method getMethod = object.getClass().getMethod(getMethodName);//获取get方法
                    Object val = getMethod.invoke(object);

                    Object convertObj = convertList.get(i);
                    if (convertObj == null) convertObj = new DefaultConvert();
                    ModelConvert c = (ModelConvert) convertObj;
                    Object convertVal = c.convert(val);

                    if (convertVal != null) {
                        HSSFRichTextString richString = new HSSFRichTextString(convertVal.toString());
                        richString.applyFont(font3);
                        cell.setCellValue(richString);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

        //生成表格
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        String path = DateUtils.getDate("yyyyMMdd") + RandomUtil.generateRandomNum( 5) + ".xls";
        try {
            FileOutputStream fos = new FileOutputStream(new File(dir + "/" + path));
            workbook.write(fos);//将workbook通过流写到文件
            fos.flush();
            workbook.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
    //sheet数据处理

    /**
     * sheet数据处理
     * @param workbook 工作薄
     * @param sheetName sheet的名字
     * @param titleList 表头名称列表
     * @param filedList 字段名称列表
     * @param convertList 需要转换的数据列表
     * @param objectList 数据列表
     */
    public static void sheetDataHandle( HSSFWorkbook workbook,String sheetName,
                                 List<String> titleList,
                                 List<String> filedList,
                                 List<ModelConvert> convertList,
                                 List objectList){
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        HSSFCellStyle style3 = workbook.createCellStyle();
        style3.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体

        // 把字体应用到当前的样式
        style3.setFont(font2);

        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLACK.index);
        font3.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);



      /*  // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("飞扬科技");*/

        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell;
        for (short i = 0; i < titleList.size(); i++) {
            cell= row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(titleList.get(i));
            cell.setCellValue(text);
        }

        //把每个单元格赋值
        int index = 0;
        for (Object object : objectList) {
            index++;
            row = sheet.createRow(index);
            for (int i = 0; i < filedList.size(); i++) {
                try {
                    cell = row.createCell(i);
                    if (index % 2 == 1) {
                        cell.setCellStyle(style2);
                    } else {
                        cell.setCellStyle(style3);
                    }

                    String getMethodName = "get" + filedList.get(i);
                    Method getMethod = object.getClass().getMethod(getMethodName);//获取get方法
                    Object val = getMethod.invoke(object);

                    Object convertObj = convertList.get(i);
                    if (convertObj == null) convertObj = new DefaultConvert();
                    ModelConvert c = (ModelConvert) convertObj;
                    Object convertVal = c.convert(val);

                    if (convertVal != null) {
                        HSSFRichTextString richString = new HSSFRichTextString(convertVal.toString());
                        richString.applyFont(font3);
                        cell.setCellValue(richString);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static List<List> readExcel(InputStream is,String path, Integer cellNumber) {
        List<List> list = new ArrayList<List>();
        try {
            if (path.endsWith(".xlsx")) {
                list = readXlsx(is, cellNumber);
            } else if (path.endsWith(".xls")) {
                list = readXls(is, cellNumber);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    //读取excel表格内容(excel2007以上)
    public static List<List> readXlsx(InputStream is, Integer cellNumber) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        List<List> list = new ArrayList<List>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    List<String> rowList = new ArrayList<String>();
                    for (int no = 0; no < cellNumber; no++) {
                        XSSFCell cell = xssfRow.getCell(no);
                        rowList.add(getValue(cell));
                    }
                    list.add(rowList);
                }
            }
        }
        return list;
    }

    //读取excel表格内容(excel2003以下)
    public static List readXls(InputStream is, Integer cellNumber) throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<List> list = new ArrayList<List>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    List<String> rowList = new ArrayList<String>();
                    for (int no = 0; no < cellNumber; no++) {
                        HSSFCell cell = hssfRow.getCell(0);
                        rowList.add(getValue(cell));
                    }
                }
            }
        }
        return list;
    }

    //获取单元格的值
    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            DecimalFormat df = new DecimalFormat("0");
            return df.format(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

    //获取单元格的值
    private static String getValue(XSSFCell xssfRow) {
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            DecimalFormat df = new DecimalFormat("0");
            return df.format(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }
}
