package com.ketansoft.dms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ketansoft.dms.service.DormRoomService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.ketansoft.dms.entity.DepartmentEntity;
import com.ketansoft.dms.service.DepartmentService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-24 19:07:50
 */
@Controller
@RequestMapping("department")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;


	
	@RequestMapping("/department.html")
	public String list(){
		return "department/department.html";
	}
	
	@RequestMapping("/department_add.html")
	public String add(){
		return "department/department_add.html";
	}


	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("department:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if(page!=null&&limit!=null) {
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);
		}
		
		//查询列表数据
		List<DepartmentEntity> departmentList = departmentService.queryList(map);
		if(page!=null&&limit!=null) {
			int total = departmentService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(departmentList, total, limit, page);

			return R.ok().put("page", pageUtil);
		}
		return R.ok().put("departmentList",departmentList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{dpmId}")
	@RequiresPermissions("department:info")
	public R info(@PathVariable("dpmId") Integer dpmId){
		DepartmentEntity department = departmentService.queryObject(dpmId);
		
		return R.ok().put("department", department);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("department:save")
	public R save(@RequestBody DepartmentEntity department){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dpmName",department.getDpmName());
		List<DepartmentEntity>sameList=departmentService.querySameName(map);
		if(sameList.size()>0){return R.error("已存在该部门");}

		departmentService.save(department);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("department:update")
	public R update(@RequestBody DepartmentEntity department){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dpmName",department.getDpmName());
		List<DepartmentEntity>sameList=departmentService.querySameName(map);
		if(sameList.size()>0){return R.error("已存在该部门");}
		departmentService.update(department);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("department:delete")
	public R delete(@RequestBody Integer[] dpmIds){
		departmentService.deleteBatch(dpmIds);
		return R.ok();
	}
	
}
