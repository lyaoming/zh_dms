package com.ketansoft.dms.controller;


import com.framework.utils.PageUtils;
import com.ketansoft.dms.entity.DormRoomEntity;
import com.ketansoft.dms.entity.DprelationshipEntity;
import com.ketansoft.dms.entity.Query;
import com.ketansoft.dms.service.DormRoomService;
import com.ketansoft.dms.service.DprelationshipService;
import com.ketansoft.dms.service.ExportDpRecordService;
import org.apache.ibatis.logging.LogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.framework.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/exprotdprecord")
public class ExportDpRecordController {
    @Autowired
    private DprelationshipService dprelationshipService;
    @Autowired
    private ExportDpRecordService exportDpRecordService;

    @Autowired
    private DormRoomService dormRoomService;

    private static Logger logger = Logger.getLogger(LogException.class.getName());

    private static  List<DprelationshipEntity>exportList=new ArrayList<>();

    @ResponseBody
    @RequestMapping("/exprotall")
    public R exportAllRecord(HttpServletResponse response, HttpServletRequest request){
        Map<String,Object>map = new HashMap<>();

        exportList = new ArrayList<>();

        //查询所有
        List<DprelationshipEntity>dprecordList = dprelationshipService.queryRecordList(map);
        for(DprelationshipEntity dprelationshipEntity:dprecordList){
            Integer rId=dprelationshipEntity.getrId();
            DormRoomEntity dormRoomEntity = dormRoomService.queryObjectG(rId);
            if (dormRoomEntity!= null) {
                dprelationshipEntity.setRoomName(dormRoomEntity.getRoomName());
            }
        }
        exportList = dprecordList;
        return R.ok().put("list",dprecordList);
    }

    @ResponseBody
    @RequestMapping("/exportselect")
    public R exportSelectRecord(@RequestBody Integer[] pdIds,HttpServletResponse response, HttpServletRequest request){

        List<DprelationshipEntity>exportSelect = dprelationshipService.exportSelect(pdIds);

        exportList = new ArrayList<>();

        for(DprelationshipEntity dprelationshipEntity:exportSelect){
            Integer rId=dprelationshipEntity.getrId();
            DormRoomEntity dormRoomEntity = dormRoomService.queryObjectG(rId);
            if (dormRoomEntity!= null) {
                dprelationshipEntity.setRoomName(dormRoomEntity.getRoomName());
            }
        }
        exportList = exportSelect;

        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/recordlist")
    public R exportquery(@RequestBody Query query){
        Map<String,Object>map=new HashMap<>();
        List<DprelationshipEntity> dprelationshipList = new ArrayList<>();
        exportList = new ArrayList<>();
        if(query.getDorm()!=null&&!query.getDorm().isEmpty())map.put("dorm",query.getDorm());
        if(query.getpName()!=null&&!query.getpName().isEmpty())map.put("pName",query.getpName());
        if(query.getSbgTime()!=null&&!query.getSbgTime().isEmpty())map.put("sbgTime",query.getSbgTime());
        if(query.getSendTime()!=null&&!query.getSendTime().isEmpty())map.put("sendTime",query.getSendTime());
        if(query.getDeposit()!=null)map.put("deposit",query.getDeposit());

        System.out.println(map);
        int i=0;
        if(query.getPage()==1){
            exportList = dprelationshipService.queryRecordList(map);
            for(DprelationshipEntity dprelationshipEntity:exportList){
                Integer rId=dprelationshipEntity.getrId();
                DormRoomEntity dormRoomEntity = dormRoomService.queryObjectG(rId);
                if (dormRoomEntity!= null) {
                    dprelationshipEntity.setRoomName(dormRoomEntity.getRoomName());
                }
                if(i<query.getLimit()){
                    dprelationshipList.add(dprelationshipEntity);
                }
                i++;
            }
        }

        map.put("limit",query.getLimit());
        map.put("offset", (query.getPage()- 1) *query.getLimit());
        int total = dprelationshipService.queryRecordTotal(map);
        if(query.getPage()>1){
            dprelationshipList= dprelationshipService.queryRecordList(map);
            for(DprelationshipEntity dprelationshipEntity:dprelationshipList){
                Integer rId=dprelationshipEntity.getrId();
                DormRoomEntity dormRoomEntity = dormRoomService.queryObjectG(rId);
                if (dormRoomEntity!= null) {
                    dprelationshipEntity.setRoomName(dormRoomEntity.getRoomName());
                }
            }
        }
        PageUtils pageUtil = new PageUtils( dprelationshipList,total,query.getLimit(),query.getPage());

        return R.ok().put("page", pageUtil);
    }

    @ResponseBody
    @RequestMapping("/downloadExcel")
    public R exportReport(HttpServletResponse response, HttpServletRequest request)  {
        if(exportList.size()==0){return R.error("导出失败！导出内容为空");}
        try {
           exportDpRecordService.exportDpRecordReport(response,request,exportList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "excel导出错误", e);
            return R.error("失败");
        }
        return R.ok("成功");
    }


}
