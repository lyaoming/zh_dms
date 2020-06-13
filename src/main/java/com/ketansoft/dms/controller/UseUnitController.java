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

import com.ketansoft.dms.entity.UseUnitEntity;
import com.ketansoft.dms.service.UseUnitService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-05 15:26:38
 */
@Controller
@RequestMapping("useunit")
public class UseUnitController {
	@Autowired
	private UseUnitService useUnitService;
	
	@RequestMapping("/useunit.html")
	public String list(){
		return "useunit/useunit.html";
	}
	
	@RequestMapping("/useunit_add.html")
	public String add(){
		return "useunit/useunit_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("useunit:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if(page!=null&&limit!=null) {
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);
		}
		
		//查询列表数据
		List<UseUnitEntity> useUnitList = useUnitService.queryList(map);
		if(page!=null&&limit!=null) {
			int total = useUnitService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(useUnitList, total, limit, page);

			return R.ok().put("page", pageUtil);
		}
		return R.ok().put("useUnitList",useUnitList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{tId}")
	@RequiresPermissions("useunit:info")
	public R info(@PathVariable("tId") Integer tId){
		UseUnitEntity useUnit = useUnitService.queryObject(tId);
		
		return R.ok().put("useUnit", useUnit);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("useunit:save")
	public R save(@RequestBody UseUnitEntity useUnit){
		useUnitService.save(useUnit);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("useunit:update")
	public R update(@RequestBody UseUnitEntity useUnit){
		useUnitService.update(useUnit);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("useunit:delete")
	public R delete(@RequestBody Integer[] tIds){
		useUnitService.deleteBatch(tIds);
		
		return R.ok();
	}
	
}
