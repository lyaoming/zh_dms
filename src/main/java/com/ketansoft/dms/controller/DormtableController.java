package com.ketansoft.dms.controller;

import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.ketansoft.dms.entity.DormtableEntity;
import com.ketansoft.dms.service.DormtableService;
import com.ketansoft.dms.service.DprelationshipService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
@Controller
@RequestMapping("dormtable")
public class DormtableController {
    @Autowired
    private DormtableService dormtableService;

    @Autowired
    private DprelationshipService dprelationshipService;

    @RequestMapping("/dormtable.html")
    public String list(){
        return "dormtable/dormtable.html";
    }

    List<DormtableEntity> exportList=new ArrayList<DormtableEntity>();


    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("dormtable:list")
    public R list(String bgTime,String endTime, Integer page, Integer limit){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bgTime",bgTime);
        map.put("endTime",endTime);
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<DormtableEntity> dormList;
        if(!com.alibaba.druid.util.StringUtils.isEmpty(bgTime)&&!com.alibaba.druid.util.StringUtils.isEmpty(endTime)){
            dormList=exportList=dormtableService.queryTimeList(map);
        }else{
            dormList=exportList=dormtableService.queryList(map);
        }

        int total = dormtableService.queryTotal(map);

        PageUtils pageUtil = new PageUtils(dormList, total, limit, page);
        return R.ok().put("page", pageUtil);
    }

    @ResponseBody
    @RequestMapping("/controllist1")
    public R controllist1(String dorm,Integer dSpecification,Integer page, Integer limit){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dorm",dorm);
        map.put("dSpecification",dSpecification);
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<DormtableEntity> dormList=dormtableService.ControlList1(map);


        int total = dormtableService.ControlTotal1(map);

        PageUtils pageUtil = new PageUtils(dormList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }

    @ResponseBody
    @RequestMapping("/controllist2")
    public R controllist2(String dorm,Integer dSpecification,Integer page, Integer limit){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dorm",dorm);
        map.put("dSpecification",dSpecification);
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<DormtableEntity> dormList=dormtableService.ControlList2(map);


        int total = dormtableService.ControlTotal2(map);

        PageUtils pageUtil = new PageUtils(dormList, total, limit, page);

        return R.ok().put("page", pageUtil);
    }


    @ResponseBody
    @RequestMapping("/showdata")
    public R showdata(){
        Map<String, Object> map = new HashMap<String, Object>();

        List res =new ArrayList();

        int sum=0;//总房间数

        int nullnum=0;//总空房数

        int nownum=0;//总现住数

        List<DormtableEntity> dormList=dormtableService.queryList(map);

        for(DormtableEntity dormtableEntity :dormList){

            nullnum=nullnum+dormtableEntity.getdNull();

            sum=sum+dormtableEntity.getDAllnum();

            nownum=nownum+dormtableEntity.getDNum();
        }

        Double reta= new BigDecimal((float)nownum/sum*100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        res.add(sum);

        res.add(nownum);

        res.add(nullnum);

        res.add(reta);

        return R.ok().put("res",res);
    }

    @ResponseBody
    @RequestMapping("/exportAll")
    public R allList(){
        exportList=dormtableService.excelList();
        return R.ok();
    }
    @ResponseBody
    @RequestMapping("/exportSelect")
    public R selectList(@RequestBody Integer[] dIds){
        exportList=new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dIds",dIds);
        exportList=dormtableService.selectList(map);
        return R.ok();
    }

    @ResponseBody
    @RequestMapping("/downloadExcel")
    public R exportReport(HttpServletResponse response, HttpServletRequest request)  {
        if(exportList.size()==0){return R.error("导出失败！导出内容为空");}
        try {
            dormtableService.exportDormReport(response,request,exportList);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("失败");
        }
        return R.ok("成功");
    }

}
