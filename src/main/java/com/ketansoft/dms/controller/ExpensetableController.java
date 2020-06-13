package com.ketansoft.dms.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.framework.utils.PageUtils;
import com.framework.utils.R;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ketansoft.dms.entity.*;
import com.ketansoft.dms.service.*;
import net.sf.json.JSONArray;
import org.apache.ibatis.logging.LogException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
@Controller
@RequestMapping("expensetable")
public class ExpensetableController {
    @Autowired
    private ExpensetableService expensetableService;
    @Autowired
    private DprelationshipService dprelationshipService;
    @Autowired
    private MonthService monthService;
    @Autowired
    private RecordService recordService;

    @RequestMapping("/expensetable.html")
    public String list(){
        return "expensetable/expensetable.html";
    }

    List<ExpensetableEntity> exportList=new ArrayList<ExpensetableEntity>();

    Long nowDate=new Date().getTime();

    SimpleDateFormat ydf=new SimpleDateFormat("yyyy");



    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list(String pDepartment,Integer pCategroy,String year,String month, Integer page, Integer limit){
        Map<String, Object> map = new HashMap<String, Object>();
        if(StringUtils.isEmpty(month)){
            map.put("month",0);
        }else{
            map.put("month",month);
        }
        map.put("year",year);
        map.put("pCategroy", pCategroy);
        map.put("pDepartment", pDepartment);
        if (page != null && limit != null) {
            map.put("offset", (page - 1) * limit);
            map.put("limit", limit);
        }
        //查询列表数据
        List<ExpensetableEntity> expenseList=expensetableService.queryList(map);
        if(expenseList!=null&&expenseList.size()>0) {
            for (int i = 0; i < expenseList.size(); i++) {
                ExpensetableEntity expensetableEntity = expenseList.get(i);
                JSONArray myJsonArray = JSONArray.fromObject(expensetableEntity.getCostItems());
                if (myJsonArray != null) {
                    expensetableEntity.setCosts(myJsonArray);
                    expensetableEntity.setCostItems(null);
                }
            }
        }
        exportList=expenseList;
        if(page!=null&&limit!=null) {
            int total = expensetableService.queryTotal(map);

            PageUtils pageUtil = new PageUtils(expenseList, total, limit, page);

            return R.ok().put("page", pageUtil);
        }
        return R.ok().put("expenseList",expenseList);
    }

    @ResponseBody
    @RequestMapping("/exportAll")
    public R allList(Integer year,Integer month){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year",year);
        map.put("month",month);
        List<ExpensetableEntity>expenseList=expensetableService.excelList(map);
        for(int i=0;i<expenseList.size();i++){
            ExpensetableEntity expensetableEntity=expenseList.get(i);
            JSONArray myJsonArray = JSONArray.fromObject(expensetableEntity.getCostItems());
            if(myJsonArray!=null){
                expensetableEntity.setCosts(myJsonArray);
                expensetableEntity.setCostItems(null);
            }
        }
        exportList=expenseList;
        return R.ok();
    }
    @ResponseBody
    @RequestMapping("/exportSelect")
    public R selectList(@RequestBody Integer[] recordIds){
        exportList=new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("recordIds",recordIds);
        List<ExpensetableEntity>expenseList=expensetableService.selectList(map);
        for(int i=0;i<expenseList.size();i++){
            ExpensetableEntity expensetableEntity=expenseList.get(i);
            JSONArray myJsonArray = JSONArray.fromObject(expensetableEntity.getCostItems());
            if(myJsonArray!=null){
                expensetableEntity.setCosts(myJsonArray);
                expensetableEntity.setCostItems(null);
            }
        }
        exportList=expenseList;
        return R.ok();
    }
    private static Logger logger = Logger.getLogger(LogException.class.getName());
    @ResponseBody
    @RequestMapping("/downloadExcel")
    public R exportReport(HttpServletResponse response, HttpServletRequest request)  {
        if(exportList.size()==0){return R.error("导出失败！导出内容为空");}
        try {
            expensetableService.exportCostReport(response,request,exportList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "excel导出错误", e);
            return R.error("失败");
        }
        return R.ok("成功");
    }


}
