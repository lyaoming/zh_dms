package com.ketansoft.dms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.ketansoft.dms.entity.CprelationshipEntity;
import com.ketansoft.dms.service.CprelationshipService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-06 17:41:18
 */
@Controller
@RequestMapping("cprelationship")
public class CprelationshipController {
	@Autowired
	private CprelationshipService cprelationshipService;
	
	@RequestMapping("/cprelationship.html")
	public String list(){
		return "cprelationship/cprelationship.html";
	}
	
	@RequestMapping("/cprelationship_add.html")
	public String add(){
		return "cprelationship/cprelationship_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<CprelationshipEntity> cprelationshipList = cprelationshipService.queryList(map);
		int total = cprelationshipService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(cprelationshipList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{rId}")
	public R info(@PathVariable("rId") Integer rId){
		Map<String,Object>map=new HashMap<>();
		map.put("rId",rId);
		List res=cprelationshipService.edqueryList(map);
		return R.ok().put("res",res);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	public R save(@RequestBody List<CprelationshipEntity> costs){
		if(costs!=null&&costs.size()>0) {
			for (int i = 0; i < costs.size(); i++) {
				CprelationshipEntity cprelationship = costs.get(i);
				if(cprelationship.getValue()!=null) {
					cprelationship.setStatus(0);
					cprelationshipService.save(cprelationship);
				}
			}
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(@RequestBody List<CprelationshipEntity> costs){
		if(costs!=null&&costs.size()>0) {
			for (int i = 0; i < costs.size(); i++) {
				CprelationshipEntity cprelationship = costs.get(i);
				if(cprelationship.getId()==null){
					cprelationship.setStatus(0);
					if(cprelationship.getValue()!=null) {
						cprelationshipService.save(cprelationship);
					}
				}
				if(cprelationship.getValue()!=null) {
					cprelationship.setStatus(1);
					cprelationship.setFlag(0);
					cprelationshipService.update(cprelationship);
				}else{
					cprelationshipService.delete(cprelationship.getId());
				}
			}
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("cprelationship:delete")
	public R delete(@RequestBody Integer[] ids){
		cprelationshipService.deleteBatch(ids);
		return R.ok();
	}
	
}
