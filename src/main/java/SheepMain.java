import module.SheepFund;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.ExcelUtil;
import util.JsoupUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 * -------------------------------------------------------------------------------------------------
 * 创建者：@author 林钦宏
 * -------------------------------------------------------------------------------------------------
 * 创建日期：2019-03-26
 * -------------------------------------------------------------------------------------------------
 * 更新历史
 * 编号|更新日期|更新人|更新内容
 */
public class SheepMain {

    public static void main(String[] args) {
        SheepFund sheepFund = JsoupUtils.getSheepFund("http://fund.eastmoney.com/161725.html?spm=search");

        List<SheepFund> fundList = new ArrayList<>();
        fundList.add(sheepFund);

        exportFund(createWorkBook(fundList));
        System.out.println("导出成功");
    }

    public static XSSFWorkbook createWorkBook(List<SheepFund> fundList) {
        //表头
        List<String> head = new ArrayList<>();
        head.add("基金名称");
        head.add("基金代码");
        head.add("基金净值");
        head.add("基金升幅");

        //内容
        List<List<String>> data = new ArrayList<>();
        for (SheepFund item : fundList) {
            List<String> items = new ArrayList<>();
            items.add(item.getName());
            items.add(item.getCode());
            items.add(item.getNetWorth());
            items.add(item.getIncrease());

            data.add(items);
        }
        return ExcelUtil.generateExport(head, data);
    }

    private static void exportFund(XSSFWorkbook workbook) {
        FileOutputStream fop = null;
        File file;
        String content = "";

        if (workbook == null) {
            System.out.println("数据为空");
            return;
        }

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            String name = "基金" + simpleDateFormat.format(new Date()) + ".xlsx";
            file = new File(name);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
