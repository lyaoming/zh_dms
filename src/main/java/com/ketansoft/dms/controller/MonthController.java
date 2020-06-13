package com.ketansoft.dms.controller;

import java.text.SimpleDateFormat;
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

import com.ketansoft.dms.entity.MonthEntity;
import com.ketansoft.dms.service.MonthService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import java.util.Date;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-08 13:36:25
 */
@Controller
@RequestMapping("month")
public class MonthController {
	@Autowired
	private MonthService monthService;
	
	@RequestMapping("/month.html")
	public String list(){
		return "month/month.html";
	}
	
	@RequestMapping("/month_add.html")
	public String add(){
		return "month/month_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if(page!=null&&limit!=null) {
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);
		}
		
		//查询列表数据
		List<MonthEntity> monthList = monthService.queryList(map);
		if(page!=null&&limit!=null) {
			int total = monthService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(monthList, total, limit, page);

			return R.ok().put("page", pageUtil);
		}
		return R.ok().put("monthList",monthList);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{mId}")
	@RequiresPermissions("month:info")
	public R info(@PathVariable("mId") Integer mId){
		MonthEntity month = monthService.queryObject(mId);
		
		return R.ok().put("month", month);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("month:save")
	public R save(@RequestBody MonthEntity month) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date bgTime = format.parse(month.getMBgtime());
		Date edTime = format.parse(month.getMEndtime());
		Long d1=bgTime.getTime();
		Long d2=edTime.getTime();
		if(d1-d2>0){
			return R.error("开始时间必须要大于结束时间");
		}
		monthService.save(month);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("month:update")
	public R update(@RequestBody MonthEntity month){
		monthService.update(month);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("month:delete")
	public R delete(@RequestBody Integer[] mIds){
		monthService.deleteBatch(mIds);
		
		return R.ok();
	}
	
}
