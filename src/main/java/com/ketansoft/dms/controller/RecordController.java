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

import com.ketansoft.dms.entity.RecordEntity;
import com.ketansoft.dms.service.RecordService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-08 17:27:52
 */
@Controller
@RequestMapping("record")
public class RecordController {
	@Autowired
	private RecordService recordService;
	
	@RequestMapping("/record.html")
	public String list(){
		return "record/record.html";
	}
	
	@RequestMapping("/record_add.html")
	public String add(){
		return "record/record_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("record:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<RecordEntity> recordList = recordService.queryList(map);
		int total = recordService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(recordList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{recordId}")
	@RequiresPermissions("record:info")
	public R info(@PathVariable("recordId") Integer recordId){
		RecordEntity record = recordService.queryObject(recordId);
		
		return R.ok().put("record", record);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("record:save")
	public R save(@RequestBody RecordEntity record){
		recordService.save(record);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("record:update")
	public R update(@RequestBody RecordEntity record){
		recordService.update(record);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("record:delete")
	public R delete(@RequestBody Integer[] recordIds){
		recordService.deleteBatch(recordIds);
		
		return R.ok();
	}
	
}
