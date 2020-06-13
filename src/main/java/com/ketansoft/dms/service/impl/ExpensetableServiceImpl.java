package com.ketansoft.dms.service.impl;
import java.util.logging.Logger;
import java.util.logging.Level;
import com.ketansoft.dms.entity.CostEntity;
import com.ketansoft.dms.entity.CprelationshipEntity;
import com.ketansoft.dms.entity.ExpensetableEntity;
import com.ketansoft.dms.mapper.CostDao;
import com.ketansoft.dms.mapper.ExpensetableDao;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ketansoft.dms.service.CprelationshipService;
import com.ketansoft.dms.service.ExpensetableService;
import org.apache.ibatis.logging.LogException;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.apache.velocity.runtime.directive.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
@Service("expensetableService")
public class ExpensetableServiceImpl implements ExpensetableService {
    @Autowired
    private ExpensetableDao expensetableDao;

    @Autowired
    private CostDao costDao;

    @Autowired
    private CprelationshipService cprelationshipService;

    @Override
    public List<ExpensetableEntity>queryList(Map<String, Object> map){return expensetableDao.queryList(map);}


    @Override
    public List<ExpensetableEntity>excelList(Map<String, Object> map){return expensetableDao.excelList(map);}

    @Override
    public List<ExpensetableEntity>selectList(Map<String,Object>map){return expensetableDao.selectList(map);}


    @Override
    public int queryTotal(Map<String, Object> map){ return expensetableDao.queryTotal(map);}


    @Override
    public void exportCostReport(HttpServletResponse response, HttpServletRequest request,List exportList) throws Exception {
        String templetPath = ExpensetableServiceImpl.class.getClassLoader().getResource("import-template/cost.xlsx").getPath();
           try {
               templetPath = java.net.URLDecoder.decode(templetPath, "UTF-8");
               } catch (Exception e) {
                      e.printStackTrace();
               }
        XSSFWorkbook workbook = getSelectWorkbook(templetPath);
        fillExcel(workbook,exportList);
        outPutExcel(workbook, "费用报表", response);
    }

        public void fillExcel(XSSFWorkbook workbook,List exportList) {

        Map<String,Object>map=new HashMap<>();
        List<CostEntity>costNameList=costDao.queryList(map);
        Integer[] costIdList=new Integer[costNameList.size()];
        for(int i=0;i<costNameList.size();i++){
            CostEntity costId=costNameList.get(i);
            costIdList[i]=costId.getCId();
        }
        List<ExpensetableEntity> expenseList =exportList;

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
        cellStyle1.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        cellStyle1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCell cell = null;
        //第一行数据的列数
        int fristRowNum=0;

//            //设置第一行数据
//            XSSFRow firstRow = sheet.getRow(0);
//            XSSFCell firstCell = firstRow.getCell(0);
//            XSSFCellStyle style = workbook.createCellStyle();
//            style.setWrapText(true);//
//            style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
//            style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
//            // 设置单元格字体
//            XSSFFont font = workbook.createFont();
//            font.setFontName("宋体");
//            font.setFontHeight((short) 250);
//            style.setFont(font);
//            firstCell.setCellStyle(style);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            int firstRows=0;

            XSSFRow fristrow = sheet.createRow(firstRows);
            fristrow.setHeight((short)750);//设置表格高度
            cell= getCell(cellStyle1,fristrow,0);
            cell.setCellValue("序号");

            cell= getCell(cellStyle1,fristrow,1);
            cell.setCellValue("姓名");

            cell= getCell(cellStyle1,fristrow,2);
            cell.setCellValue("部室");

            cell= getCell(cellStyle1,fristrow,3);
            cell.setCellValue("入住房屋");

            cell= getCell(cellStyle1,fristrow,4);
            cell.setCellValue("年份");

            cell= getCell(cellStyle1,fristrow,5);
            cell.setCellValue("月份");

            int fristindex=6;
            int last=0;
            for(int i=0;i<costNameList.size();i++){
                CostEntity costName=costNameList.get(i);
                cell= getCell(cellStyle1,fristrow,fristindex++);
                cell.setCellValue(costName.getCName());
                last=i+1;
            }
            if(costIdList.length>0) {
                cell = getCell(cellStyle1, fristrow, last + 6);
                cell.setCellValue("总额");
            }

            int rows =1;
            int ColNumber=0;
            int i = 1; //序号,从一开始
            for (ExpensetableEntity expensetableEntity : expenseList) {

                XSSFRow row = sheet.createRow(rows);
                row.setHeight((short)700);//设置表格高度
                /**-------------------设置数据-------------------*/

                //序号
                int index =0;
                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(i++);

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(expensetableEntity.getPName());

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(expensetableEntity.getPDepartment());

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(expensetableEntity.getDorm());

                cell = getCell(cellStyle, row, index++);
                if(expensetableEntity.getRecordYear()!=null) {
                    cell.setCellValue(expensetableEntity.getRecordYear());
                }else{
                    cell.setCellValue(0);
                }

                cell = getCell(cellStyle, row, index++);
                if(expensetableEntity.getRecordMonth()!=null) {
                    cell.setCellValue(expensetableEntity.getRecordMonth());
                }else{
                    cell.setCellValue(0);
                }
                /**--------------------------------------------------------------**/
                List textlist=expensetableEntity.getCosts();
                Double sum=0.00;
                int lastIndex=0;
                for(int j=0;j<costIdList.length;j++){
                    if(textlist!=null&&textlist.size()>0) {
                        cell = getCell(cellStyle, row, 6 + j);
                        cell.setCellValue(0);
                        for (int k = 0; k < textlist.size(); k++) {
                            net.sf.json.JSONObject Object = (net.sf.json.JSONObject) textlist.get(k);
                            if (Object.getInt("CId") == costIdList[j]) {
                                cell = getCell(cellStyle, row, 6 + j);
                                cell.setCellValue(Object.getString("value"));
                                sum = sum + Object.getDouble("value");
                            }
                        }
                    }else{
                        cell = getCell(cellStyle, row, 6 + j);
                        cell.setCellValue(0);
                    }
                    lastIndex=j+7;
                }
                cell = getCell(cellStyle, row,lastIndex);
                cell.setCellValue(sum);
                /**----------------------------------------------*/
                ColNumber=index;
                rows++;
            }
            //设置每一列宽度
            for(int a=1;a<=ColNumber;a++){
                sheet.setColumnWidth(a, sheet.getColumnWidth(a) * 15/ 10);
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
