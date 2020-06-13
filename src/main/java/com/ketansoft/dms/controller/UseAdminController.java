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

import com.ketansoft.dms.entity.UseAdminEntity;
import com.ketansoft.dms.service.UseAdminService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-05 09:48:57
 */
@Controller
@RequestMapping("useadmin")
public class UseAdminController {
	@Autowired
	private UseAdminService useAdminService;
	
	@RequestMapping("/useadmin.html")
	public String list(){
		return "useadmin/useadmin.html";
	}
	
	@RequestMapping("/useadmin_add.html")
	public String add(){
		return "useadmin/useadmin_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("useadmin:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if(page!=null&&limit!=null) {
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);
		}
		//查询列表数据
		List<UseAdminEntity> useAdminList = useAdminService.queryList(map);
		if(limit!=null&&page!=null) {
			int total = useAdminService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(useAdminList, total, limit, page);

			return R.ok().put("page", pageUtil);
		}
		return R.ok().put("useAdminList",useAdminList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{uId}")
	@RequiresPermissions("useadmin:info")
	public R info(@PathVariable("uId") Integer uId){
		UseAdminEntity useAdmin = useAdminService.queryObject(uId);
		
		return R.ok().put("useAdmin", useAdmin);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("useadmin:save")
	public R save(@RequestBody UseAdminEntity useAdmin){
		useAdminService.save(useAdmin);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("useadmin:update")
	public R update(@RequestBody UseAdminEntity useAdmin){
		useAdminService.update(useAdmin);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("useadmin:delete")
	public R delete(@RequestBody Integer[] uIds){
		useAdminService.deleteBatch(uIds);
		
		return R.ok();
	}
	
}
