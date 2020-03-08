package com.practice.document;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author XiaoSi
 * @className DocumentHandlerTest
 * @description TODO
 * @date 2020/2/6
 */
@Slf4j
public class DocumentHandlerTest {

    private static final List<String> TARGETS = Arrays.asList("租赁条件", "租金分段计息条件信息", "租金计划");
    private static List<Map<String,String>> DICT_ITEMS;

    @Test
    public void uploadExcelTest01() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("G:\\GooGle\\amar\\M.xls");
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            List<Integer> rows = new ArrayList<>();
            for (String currentName : TARGETS) {
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                for (int rowIndex = firstRowNum; rowIndex < lastRowNum + 1; rowIndex++) {
                    HSSFRow row = sheet.getRow(rowIndex);
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        if (currentName.equals(cell.toString())) {
                            System.out.println(MessageFormat.format("{0}：{1}", currentName, row.getRowNum()));
                            rows.add(row.getRowNum());
                        }
                    }
                }
            }
            System.out.println(Arrays.toString(rows.toArray()));
            HSSFCell cell = sheet.getRow(0).getCell(0);
            System.out.println(cell);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private <T> T classCast(Object t, Class<T> clazz) {
        return clazz.cast(t);
    }

    @Test
    public void testString01(){
        String str = "3";
        System.out.println(Integer.parseInt(str));
        String doubleStr = "3.00";
        System.out.println((int)Double.parseDouble(doubleStr));
        System.out.println(Integer.valueOf(doubleStr,2));
    }

    @Test
    public void testRegex01(){
        String value = "2019-十二/27 00:00:00";
        Pattern pattern = Pattern.compile("[-|/].*[-|/]");
        Matcher matcher = pattern.matcher(value);
        if (matcher.find()) {
            value = matcher.replaceFirst("-12-");
        }
        System.out.println(value);
    }
}
