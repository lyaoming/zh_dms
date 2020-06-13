package com.ketansoft.dms.service.impl;

import com.ketansoft.dms.entity.DormtableEntity;
import com.ketansoft.dms.mapper.DormtableDao;
import com.ketansoft.dms.service.DormtableService;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
@Service("dormtable")
public class DormtableServiceImpl implements DormtableService {
    @Autowired
    private DormtableDao dormtableDao;

    @Override
    public List<DormtableEntity> queryList(Map<String, Object> map){return dormtableDao.queryList(map);}

    @Override
    public List<DormtableEntity> queryTimeList(Map<String, Object> map){return dormtableDao.queryTimeList(map);}


    @Override
    public List<DormtableEntity>excelList(){return dormtableDao.excelList();}

    @Override
    public List<DormtableEntity>selectList(Map<String,Object>map){return dormtableDao.selectList(map);}

    @Override
    public List<DormtableEntity> ControlList1(Map<String, Object> map){return dormtableDao.ControlList1(map);}

    @Override
    public List<DormtableEntity> ControlList2(Map<String, Object> map){return dormtableDao.ControlList2(map);}


    @Override
    public int queryTotal(Map<String, Object> map){ return dormtableDao.queryTotal(map);}

    @Override
    public int ControlTotal1(Map<String, Object> map){ return dormtableDao.ControlTotal1(map);}

    @Override
    public int ControlTotal2(Map<String, Object> map){ return dormtableDao.ControlTotal2(map);}

    @Override
    public void exportDormReport(HttpServletResponse response, HttpServletRequest request,List exportList) throws Exception {
        String templetPath = ExpensetableServiceImpl.class.getClassLoader().getResource("import-template/dorm.xlsx").getPath();
        try {
            templetPath = java.net.URLDecoder.decode(templetPath, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        XSSFWorkbook workbook = getSelectWorkbook(templetPath);
        fillExcel(workbook,exportList);
        outPutExcel(workbook, "房屋使用汇总表", response);
    }

    public void fillExcel(XSSFWorkbook workbook,List exportList) {

        List<DormtableEntity> dormList =exportList ;
        XSSFCellStyle cellStyle = getXssfCellStyle(workbook);
        // 设置单元格字体
        XSSFFont font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeight((short) 250);
        cellStyle.setFont(font1);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();

        int nowNum=0;

        int allNum=0;

        int emptyNum=0;


        XSSFCell cell = null;


        int ColNumber=0;
        if (dormList!=null && dormList.size()>0){
            int i = 1; //序号,从一开始
            for (DormtableEntity dormtableEntity : dormList) {
                XSSFRow row = sheet.createRow(rows);
                row.setHeight((short)700);//设置表格高度
                /**-------------------设置数据-------------------*/

                //序号
                int index =0;
                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(i++);

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormtableEntity.getDAddress());

                cell = getCell(cellStyle, row, index++);
                if(dormtableEntity.getDType()==1){
                cell.setCellValue("合并");}else{
                    cell.setCellValue("拆分");
                }

                cell = getCell(cellStyle, row, index++);
                if(dormtableEntity.getDSpecification()==1){
                    cell.setCellValue("2房一厅");}else{
                    cell.setCellValue("3房一厅");
                }

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormtableEntity.getUseUnit());

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormtableEntity.getUseAdmin());


                cell = getCell(cellStyle, row, index++);
                if(dormtableEntity.getDNum()!=null) {
                    cell.setCellValue(dormtableEntity.getDNum());
                }else{ cell.setCellValue(0);}

                cell = getCell(cellStyle,row, index++);
                if(dormtableEntity.getDAllnum()!=null) {
                    cell.setCellValue(dormtableEntity.getDAllnum());
                }else{
                    cell.setCellValue(0);
                }

                cell = getCell(cellStyle, row, index++);
                if(dormtableEntity.getdNull()!=null) {
                    cell.setCellValue(dormtableEntity.getdNull());
                }else{
                    cell.setCellValue(0);
                }

                cell = getCell(cellStyle, row, index++);
                if(dormtableEntity.getOccupancyRate()!=null) {
                    cell.setCellValue(dormtableEntity.getOccupancyRate());
                }else{
                    cell.setCellValue(0);
                }


                /**----------------------------------------------*/
                nowNum=nowNum+dormtableEntity.getDNum();
                emptyNum=emptyNum+dormtableEntity.getdNull();
                allNum=allNum+dormtableEntity.getDAllnum();
                ColNumber=index;
                rows++;
            }
            //设置汇总列
            XSSFRow row = sheet.createRow(rows);
            row.setHeight((short)750);//设置表格高度
            cell = getCell(cellStyle, row,ColNumber-5);
            cell.setCellValue("汇总");
            Double liveNum= new BigDecimal((float)nowNum/allNum*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            cell = getCell(cellStyle, row, --ColNumber);
            if(liveNum!=null) {
                cell.setCellValue(liveNum/100);
            }else{ cell.setCellValue(0);}
            cell = getCell(cellStyle, row, --ColNumber);
            cell.setCellValue(emptyNum);
            cell = getCell(cellStyle, row, --ColNumber);
            cell.setCellValue(allNum);
            cell = getCell(cellStyle, row, --ColNumber);
            cell.setCellValue(nowNum);



            //设置每一列宽度
            for(int a=1;a<=ColNumber;a++){
                sheet.setColumnWidth(a, sheet.getColumnWidth(a) * 15/ 10);
            }
        }


        //设置第一行数据
        XSSFRow firstRow = sheet.getRow(0);
        XSSFCell firstCell = firstRow.getCell(0);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setWrapText(true);//
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
        // 设置单元格字体
        XSSFFont font = workbook.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
        font.setFontName("宋体");
        font.setFontHeight((short)300);
        style.setFont(font);
        firstCell.setCellStyle(style);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Double num= new BigDecimal((float)nowNum/allNum*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        firstCell.setCellValue("房屋汇总报表  导出时间："+sdf.format(new Date()));
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

        // 设置表格线
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
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
