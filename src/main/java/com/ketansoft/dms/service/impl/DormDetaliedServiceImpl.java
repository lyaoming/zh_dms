package com.ketansoft.dms.service.impl;

import com.ketansoft.dms.entity.DgrelationshipEntity;
import com.ketansoft.dms.entity.DormDetailedEntity;
import com.ketansoft.dms.entity.DormtableEntity;
import com.ketansoft.dms.mapper.DormDetailedDao;
import com.ketansoft.dms.mapper.DormtableDao;
import com.ketansoft.dms.service.DgrelationshipService;
import com.ketansoft.dms.service.DormDetailedService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
@Service("dormdetailed")
public class DormDetaliedServiceImpl implements DormDetailedService {
    @Autowired
    private DormDetailedDao dormDetailedDao;

    @Autowired
    private DgrelationshipService dgrelationshipService;

    @Override
    public List<DormDetailedEntity> queryList(Map<String, Object> map){return dormDetailedDao.queryList(map);}

    @Override
    public List<DormDetailedEntity> queryTimeList(Map<String, Object> map){return dormDetailedDao.queryTimeList(map);}


    @Override
    public List<DormDetailedEntity>excelList(){return dormDetailedDao.excelList();}

    @Override
    public List<DormDetailedEntity>selectList(Map<String,Object>map){return dormDetailedDao.selectList(map);}


    @Override
    public int queryTotal(Map<String, Object> map){ return dormDetailedDao.queryTotal(map);}

    @Override
    public void exportDormDetailedReport(HttpServletResponse response, HttpServletRequest request,List exportList) throws Exception {

        String templetPath = ExpensetableServiceImpl.class.getClassLoader().getResource("import-template/detailed.xlsx").getPath();
        try {
            templetPath = java.net.URLDecoder.decode(templetPath, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        XSSFWorkbook workbook = getSelectWorkbook(templetPath);
        fillExcel(workbook,exportList);
        outPutExcel(workbook, "房屋使用细明表", response);
    }

    public void fillExcel(XSSFWorkbook workbook,List exportList) {

        List<DormDetailedEntity> dormList =exportList ;
        XSSFCellStyle cellStyle = getXssfCellStyle(workbook);
        // 设置单元格字体
        XSSFFont font1 = workbook.createFont();
        font1.setFontName("宋体");
        font1.setFontHeight((short) 250);
        cellStyle.setFont(font1);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getPhysicalNumberOfRows();


        XSSFCell cell = null;


        int ColNumber=0;
        if (dormList!=null && dormList.size()>0){
            int i = 1; //序号,从一开始

            for (DormDetailedEntity dormDetailedEntity : dormList) {
                Map<String,Object>map=new HashMap<>();
                if(dormDetailedEntity.getrId()!=null){
                    map.put("rId",dormDetailedEntity.getrId());
                }
                map.put("dId",dormDetailedEntity.getDId());
                List<DgrelationshipEntity> peizhi=dgrelationshipService.peizhiList(map);
                XSSFRow row = sheet.createRow(rows);
                row.setHeight((short)1200);//设置表格高度
                /**-------------------设置数据-------------------*/

                //序号
                int index =0;
                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(i++);

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormDetailedEntity.getDAddress());

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormDetailedEntity.getRoomName());

                cell = getCell(cellStyle, row, index++);
                if(dormDetailedEntity.getDType()==1){
                cell.setCellValue("合并");}else{
                    cell.setCellValue("拆分");
                }


                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormDetailedEntity.getUseAdmin());

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormDetailedEntity.getUseUnit());


                cell = getCell(cellStyle, row, index++);
                if(dormDetailedEntity.getpId()!=null) {
                    cell.setCellValue("是");
                }else{
                    cell.setCellValue("否");
                }

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormDetailedEntity.getpName());


                cell = getCell(cellStyle, row, index++);
                if(dormDetailedEntity.getpCategroy()!=null) {
                    cell.setCellValue(dormDetailedEntity.getpCategroy());
                }
                else{
                    cell.setCellValue("");
                }

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormDetailedEntity.getpDepartment());

                cell = getCell(cellStyle, row, index++);
                if(dormDetailedEntity.getpSex()!=null) {
                    if (dormDetailedEntity.getpSex() == 1) {
                        cell.setCellValue("男");
                    } else if (dormDetailedEntity.getpSex() == 0) {
                        cell.setCellValue("女");
                    }
                }else{
                    cell.setCellValue("");
                }

                cell = getCell(cellStyle, row, index++);
                cell.setCellValue(dormDetailedEntity.getpPhone());

                cell = getCell(cellStyle, row, index++);

                if(peizhi.size()>0){
                    String print="";
                    for(int j=0;j<peizhi.size();j++){
                        DgrelationshipEntity dgrelationshipEntity=peizhi.get(j);
                        print=print+(j+1)+"、"+dgrelationshipEntity.getGName()+" * "+dgrelationshipEntity.getGdNumber()+"\n  ";
                    }
                    cell.setCellValue(print);
                }else{
                    cell.setCellValue(" ");
                }
                /**----------------------------------------------*/
                ColNumber=index;
                rows++;
            }
            //设置每一列宽度
            int last=0;
            for(int a=1;a<ColNumber;a++){
                sheet.setColumnWidth(a, sheet.getColumnWidth(a) * 13/ 10);
                last=++a;
            }
            sheet.setColumnWidth(last, sheet.getColumnWidth(last) * 25/ 10);
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
        firstCell.setCellValue("房屋明细报表  导出时间："+sdf.format(new Date()));
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
