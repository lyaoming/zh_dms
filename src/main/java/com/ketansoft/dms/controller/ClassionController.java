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

import com.ketansoft.dms.entity.ClassionEntity;
import com.ketansoft.dms.service.ClassionService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 09:25:50
 */
@Controller
@RequestMapping("classion")
public class ClassionController {
	@Autowired
	private ClassionService classionService;
	
	@RequestMapping("/classion.html")
	public String list(){
		return "classion/classion.html";
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("classion:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if(page!=null&&limit!=null){
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);
		}
		
		//查询列表数据
		List<ClassionEntity> classionList = classionService.queryList(map);
		if(page!=null&&limit!=null) {
			int total = classionService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(classionList, total, limit, page);

			return R.ok().put("page", pageUtil);
		}
		return R.ok().put("classionList",classionList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("classion:info")
	public R info(@PathVariable("id") Integer id){
		ClassionEntity classion = classionService.queryObject(id);
		
		return R.ok().put("classion", classion);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("classion:save")
	public R save(@RequestBody ClassionEntity classion){
		classionService.save(classion);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("classion:update")
	public R update(@RequestBody ClassionEntity classion){
		classionService.update(classion);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("classion:delete")
	public R delete(@RequestBody Integer[] ids){
		classionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
