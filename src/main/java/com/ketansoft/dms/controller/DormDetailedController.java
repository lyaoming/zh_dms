package com.ketansoft.dms.controller;

import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.ketansoft.dms.entity.DormDetailedEntity;
import com.ketansoft.dms.entity.DormtableEntity;
import com.ketansoft.dms.entity.PersonnelEntity;
import com.ketansoft.dms.service.DgrelationshipService;
import com.ketansoft.dms.service.DormDetailedService;
import com.ketansoft.dms.service.DormtableService;
import com.ketansoft.dms.service.PersonnelService;
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
@RequestMapping("dormdetailed")
public class DormDetailedController {
    @Autowired
    private DormDetailedService dormDetailedService;

    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private DgrelationshipService dgrelationshipService;

    @RequestMapping("/dormdetailed.html")
    public String list(){
        return "dormdetailed/dormdetailed.html";
    }

    List<DormDetailedEntity> exportList=new ArrayList<>();


    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list(String bgTime,String endTime, Integer page, Integer limit){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("bgTime",bgTime);
        map.put("endTime",endTime);
        map.put("offset", (page - 1) * limit);
        map.put("limit", limit);

        //查询列表数据
        List<DormDetailedEntity> dormList;
        if(!com.alibaba.druid.util.StringUtils.isEmpty(bgTime)&&!com.alibaba.druid.util.StringUtils.isEmpty(endTime)){
            dormList=exportList=dormDetailedService.queryTimeList(map);
        }else{
            dormList=exportList=dormDetailedService.queryList(map);
        }

        if (addPersonnel(dormList) == "ok"&&getHonePeizhi(dormList)=="ok") {

                int total = dormDetailedService.queryTotal(map);

                PageUtils pageUtil = new PageUtils(dormList, total, limit, page);

                exportList=dormList;

                return R.ok().put("page", pageUtil);
            }
        return R.error();
    }

    @ResponseBody
    @RequestMapping("/exportAll")
    public R allList(){
        List<DormDetailedEntity> dormList=dormDetailedService.excelList();
        if(addPersonnel(dormList)=="ok"&&getHonePeizhi(dormList)=="ok") {
            exportList=dormList;
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @RequestMapping("/exportSelect")
    public R selectList(@RequestBody Integer[] rIds){
        exportList=new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rIds",rIds);
        List<DormDetailedEntity> dormList=dormDetailedService.selectList(map);
        if(addPersonnel(dormList)=="ok"&&getHonePeizhi(dormList)=="ok") {
            exportList=dormList;
            return R.ok();
        }
        return R.error();
    }

    @ResponseBody
    @RequestMapping("/downloadExcel")
    public R exportReport(HttpServletResponse response, HttpServletRequest request)  {
        if(exportList.size()==0){return R.error("导出失败！导出内容为空");}
        try {
            dormDetailedService.exportDormDetailedReport(response,request,exportList);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("失败");
        }
        return R.ok("成功");
    }

    //增加人员信息
    public String addPersonnel(List<DormDetailedEntity> list){
        for(int i=0;i<list.size();i++) {
            DormDetailedEntity dormDetailedEntity = list.get(i);
            if (dormDetailedEntity.getpId() != null) {
                dormDetailedEntity.setYesNo(1);
                PersonnelEntity personnelEntity = personnelService.queryObject(dormDetailedEntity.getpId());
                if (personnelEntity != null) {
                    dormDetailedEntity.setpName(personnelEntity.getPName());
                    dormDetailedEntity.setpCategroy(personnelEntity.getClassion());
                    dormDetailedEntity.setpDepartment(personnelEntity.getPDepartment());
                    dormDetailedEntity.setpSex(personnelEntity.getPSex());
                    dormDetailedEntity.setpPhone(personnelEntity.getPPhone());
                }
            } else {
                dormDetailedEntity.setYesNo(0);
            }
        }
        return "ok";
    }

    public String getHonePeizhi(List<DormDetailedEntity>list){
        Map<String,Object>map=new HashMap<>();
        for(int i=0;i<list.size();i++){
           DormDetailedEntity dormDetailedEntity=list.get(i);
            map.put("dId",dormDetailedEntity.getDId());
           if(dormDetailedEntity.getrId()!=null){
               map.put("rId",dormDetailedEntity.getrId());
           }
           List peizhi=dgrelationshipService.peizhiList(map);
           if(peizhi!=null){
               dormDetailedEntity.setHome(peizhi);
           }
        }
        return "ok";
    }

}
