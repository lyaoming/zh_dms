package com.ketansoft.dms.service.impl;

import com.ketansoft.dms.entity.DprelationshipEntity;
import com.ketansoft.dms.service.ExportDpRecordService;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("ExportDpRecordService")
public class ExportDpRecordServiceImpl implements ExportDpRecordService {

   public void exportDpRecordReport(HttpServletResponse response, HttpServletRequest request, List exportList) throws Exception{
       String templetPath = ExpensetableServiceImpl.class.getClassLoader().getResource("import-template/dprecord.xlsx").getPath();
       try {
           templetPath = java.net.URLDecoder.decode(templetPath, "UTF-8");
       } catch (Exception e) {
           e.printStackTrace();
       }
       XSSFWorkbook workbook = getSelectWorkbook(templetPath);
       fillExcel(workbook,exportList);
       outPutExcel(workbook, "入住记录报表", response);
  }
    public void fillExcel(XSSFWorkbook workbook,List exportList) {

        List<DprelationshipEntity>dpRecordList =exportList;

        XSSFCellStyle cellStyle = getXssfCellStyle(workbook);

        XSSFCellStyle cellStyle1 = getXssfCellStyle(workbook);
        // 设置单元格字体
        XSSFFont font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeight((short) 250);

        //第一行使用的字体
        XSSFFont font2 = workbook.createFont();
        font2.setFontName("宋体");
        font2.setFontHeight((short)250);
        font2.setBoldweight( XSSFFont.BOLDWEIGHT_BOLD);

        cellStyle.setFont(font1);

        cellStyle1.setFont(font2);
        cellStyle1.setBorderTop(XSSFCellStyle.BORDER_THIN);
        cellStyle1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCell cell = null;
        //第一行数据的列数

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        int firstRows=0;

        XSSFRow fristrow = sheet.createRow(firstRows);
        cellStyle1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        fristrow.setHeight((short)750);//设置表格高度
        cell= getCell(cellStyle1,fristrow,0);
        cell.setCellValue("序号");

        cell= getCell(cellStyle1,fristrow,1);
        cell.setCellValue("姓名");

        cell= getCell(cellStyle1,fristrow,2);
        cell.setCellValue("入住房屋");

        cell= getCell(cellStyle1,fristrow,3);
        cell.setCellValue("房间");

        cell= getCell(cellStyle1,fristrow,4);
        cell.setCellValue("登记入住时间");

        cell= getCell(cellStyle1,fristrow,5);
        cell.setCellValue("预计到期时间");

        cell= getCell(cellStyle1,fristrow,6);
        cell.setCellValue("退租时间");

        cell= getCell(cellStyle1,fristrow,7);
        cell.setCellValue("是否交押金");

        cell= getCell(cellStyle1,fristrow,8);
        cell.setCellValue("押金金额");

        int rows =1;
        int i = 1;
        for (int x=0;x<dpRecordList.size();x++) {
            DprelationshipEntity dprelationshipEntity = new DprelationshipEntity();
            dprelationshipEntity = dpRecordList.get(x);

            XSSFRow row = sheet.createRow(rows);
            row.setHeight((short) 700);//设置表格高度
            row.setHeight((short)700);//设置表格高度
            if(x<9) sheet.setColumnWidth(x, sheet.getColumnWidth(x) * 15/ 10);
            /**-------------------设置数据-------------------*/

            //1、序号
            int index = 0;
            cell = getCell(cellStyle, row, index++);
            cell.setCellValue(i++);

            //2、名字
            cell = getCell(cellStyle, row, index++);
            cell.setCellValue(dprelationshipEntity.getpName());

            //3、房屋号
            cell = getCell(cellStyle, row, index++);
            cell.setCellValue(dprelationshipEntity.getdAddress());

            //4、房间
            cell = getCell(cellStyle, row, index++);
            cell.setCellValue(dprelationshipEntity.getRoomName());

            //5、入住时间
            cell = getCell(cellStyle, row, index++);
            if (dprelationshipEntity.getCheckInTime() != null) {
                cell.setCellValue(sdf.format(dprelationshipEntity.getCheckInTime()));
            } else {
                cell.setCellValue(" ");
            }

            //6、预计到期时间
            cell = getCell(cellStyle, row, index++);
            if (dprelationshipEntity.getExpectedDueTime()!=null) {
                cell.setCellValue(sdf.format(dprelationshipEntity.getExpectedDueTime()));
            } else {
                cell.setCellValue(" ");
            }

            //7、退租时间
            cell = getCell(cellStyle, row, index++);
            if (dprelationshipEntity.getLeaveTime()!=null) {
                cell.setCellValue(sdf.format(dprelationshipEntity.getLeaveTime()));
            } else {
                cell.setCellValue(" ");
            }

            //8、是否交押金
            cell = getCell(cellStyle, row, index++);
            if (dprelationshipEntity.getDeposit()==1) {
                cell.setCellValue("是");
            } else if(dprelationshipEntity.getDeposit()==0){
                cell.setCellValue("否");
            }else{
                cell.setCellValue(" ");
            }

            //9、押金金额
            cell = getCell(cellStyle,row,index++);
            if(dprelationshipEntity.getDepositMoney()!=null){
                cell.setCellValue(dprelationshipEntity.getDepositMoney());
            }else{
                cell.setCellValue(0);
            }

            rows++;
        }


    }

    private void outPutExcel(XSSFWorkbook workbook, String fileName, HttpServletResponse response) throws Exception	{
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment; filename="+fileName+".xlsx");
        response.setContentType("Application/msexcel");
        workbook.write(response.getOutputStream());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    private XSSFWorkbook getSelectWorkbook(String templetPath) throws Exception {
        System.out.println("----------------------------------------");
        System.out.println("path:"+templetPath);
        System.out.println("----------------------------------------");
        XSSFWorkbook workbook = null;
        File templetFile = new File(templetPath);
        workbook = new XSSFWorkbook(new FileInputStream(templetFile));
        return workbook;
    }

    private XSSFCellStyle getXssfCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(true);//
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        // 设置表格线
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//
        // 设置单元格字体
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        style.setFont(font);
        return style;
    }

    private XSSFCell getCell(XSSFCellStyle cellStyle, XSSFRow row, int index) {
        XSSFCell cell = row.createCell(index);// 序号
        cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        cell.setCellStyle(cellStyle);
        return cell;
    }



    private File saveTempFile(InputStream is, String outputPath, String fileName) {
        File fOut = new File(outputPath, fileName);
        if(!fOut.exists()){
            (new File(fOut.getParent())).mkdirs();
        }
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fos = new FileOutputStream(fOut);
            bos = new BufferedOutputStream(fos);
            int b;
            while((b = is.read()) != -1){
                bos.write(b);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        } finally {
            try {
                if(bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        return fOut;
    }

}
