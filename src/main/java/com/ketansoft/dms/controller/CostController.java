package com.ketansoft.dms.controller;

import java.io.UnsupportedEncodingException;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.ketansoft.dms.entity.CprelationshipEntity;
import com.ketansoft.dms.entity.DprelationshipEntity;
import com.ketansoft.dms.entity.ExpensetableEntity;
import com.ketansoft.dms.service.CprelationshipService;
import com.ketansoft.dms.service.DprelationshipService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.ketansoft.dms.entity.CostEntity;
import com.ketansoft.dms.service.CostService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-26 10:31:20
 */
@Controller
@RequestMapping("cost")
public class CostController {
	@Autowired
	private CostService costService;

	@Autowired
	private CprelationshipService cprelationshipService;
	
	@RequestMapping("/cost.html")
	public String list(){
		return "cost/cost.html";
	}
	
	@RequestMapping("/cost_record.html")
	public String add(){
		return "cost/cost_record.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("cost:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if(page!=null&&limit!=null) {
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);
		}
		
		//查询列表数据
		List<CostEntity> costList = costService.queryList(map);
		if(page!=null&&limit!=null) {
			int total = costService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(costList, total, limit, page);

			return R.ok().put("page", pageUtil);
		}
		return R.ok().put("costList",costList);
	}


	@ResponseBody
	@RequestMapping("/recordlist")
	public R recordlist(String pDepartment,Integer pCategroy,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if (!com.alibaba.druid.util.StringUtils.isEmpty(pDepartment)){
			try {
				pDepartment= new String(pDepartment.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		    map.put("pDepartment", pDepartment);
		    map.put("pCategroy", pCategroy);
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);

		   //查询列表数据
		    List<ExpensetableEntity> costList = costService.queryRecordList(map);
		    for(int i=0;i<costList.size();i++){
			ExpensetableEntity expensetableEntity=costList.get(i);
			Date bgTime=expensetableEntity.getCheckInTime();
			Date endTime=expensetableEntity.getExpectedDueTime();
			map.put("pId",expensetableEntity.getpId());
			map.put("bgTime",bgTime);
			map.put("endTime",endTime);
			expensetableEntity.setCosts(cprelationshipService.queryRecordList(map));
		    }

		    int total = costService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(costList, total, limit, page);

			return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{cId}")
	@RequiresPermissions("cost:info")
	public R info(@PathVariable("cId") Integer cId){
		CostEntity cost = costService.queryObject(cId);
		
		return R.ok().put("cost", cost);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("cost:save")
	public R save(@RequestBody CostEntity cost){
		costService.save(cost);
		
		return R.ok();
	}


	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("cost:update")
	public R update(@RequestBody CostEntity cost){
		costService.update(cost);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cost:delete")
	public R delete(@RequestBody Integer[] cIds){
		costService.deleteBatch(cIds);
		return R.ok();
	}
	
}
