package com.ketansoft.dms.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import com.ketansoft.dms.entity.DepartmentEntity;
import com.ketansoft.dms.entity.DormRoomEntity;
import com.ketansoft.dms.service.DepartmentService;
import com.ketansoft.dms.service.DormRoomService;
import org.apache.ibatis.logging.LogException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.ketansoft.dms.service.DormService;
import com.ketansoft.dms.entity.PersonnelEntity;
import com.ketansoft.dms.service.PersonnelService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-17 10:08:23
 */
@Controller
@RequestMapping("personnel")
public class PersonnelController {
	@Autowired
	private PersonnelService personnelService;

	@Autowired
	private DormService dormService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DormRoomService dormRoomService;
	
	@RequestMapping("/personnel.html")
	public String list(){
		return "personnel/personnel.html";
	}

	private static Logger logger = Logger.getLogger(LogException.class.getName());

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	Date addTime=new Date();
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("personnel:list")
	public R list(String pName,String pDepartment,Integer select1,Integer select2,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pName",pName);
		map.put("pDepartment",pDepartment);
		map.put("select1",select1);
		map.put("select2",select2);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<PersonnelEntity> personnelList = personnelService.queryList(map);
		int total = personnelService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(personnelList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{pId}")
	@RequiresPermissions("personnel:info")
	public R info(@PathVariable("pId") Integer pId){
		PersonnelEntity personnel=new PersonnelEntity();
		try {
			 personnel = personnelService.queryObject(pId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "错误", e);
			return R.error("失败");
		}
		return R.ok().put("personnel", personnel);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("personnel:save")
	public R save(@RequestBody PersonnelEntity personnel){
		Map<String,Object>map=new HashMap<>();
		map.put("pName",personnel.getPName());
		map.put("pNumber",personnel.getPNumber());
		List<PersonnelEntity>Same=personnelService.querySame(map);
		//更新人员信息
		for(int i=0;i<Same.size();i++){
			PersonnelEntity personnelEntity=Same.get(i);
			personnel.setPId(personnelEntity.getPId());
			personnelService.update(personnel);
			return R.ok().put("id",personnel.getPId());
		}
		if(personnel.getPDepartment()!=null)addDpm(personnel.getPDepartment());
		personnelService.save(personnel);
		int id=personnel.getPId();
		return R.ok().put("id",id);
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("personnel:update")
	public R update(@RequestBody PersonnelEntity personnel){
		//新增部室
		if(personnel.getPDepartment()!=null)addDpm(personnel.getPDepartment());
		personnelService.update(personnel);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("personnel:delete")
	public R delete(@RequestBody Integer[] pIds){
		for(int i=0 ;i<pIds.length;i++) {
			DormRoomEntity dormRoomEntity1 = dormRoomService.queryObject(pIds[i]);
			DormRoomEntity dormRoomEntity2=new DormRoomEntity();
			dormRoomEntity2.setPId(null);
			dormRoomEntity2.setStatus(1);
			if(dormRoomEntity1!=null) {
				dormRoomEntity2.setrId(dormRoomEntity1.getrId());
				dormRoomService.update(dormRoomEntity2);
			}
			dormService.deletep(pIds[i]);
			personnelService.delete(pIds[i]);
		}
		return R.ok();
	}

	public  String addDpm(String dpm){
		Map<String,Object>map=new HashMap<>();
		//新增部室
		if(!(dpm.isEmpty())&&dpm!=null) {
			map.put("dpmName", dpm);
			List<DepartmentEntity> sameList = departmentService.querySameName(map);
			if (sameList.size() == 0) {
				DepartmentEntity departmentEntity = new DepartmentEntity();
				departmentEntity.setDpmName(dpm);
				departmentEntity.setAddTime(addTime);
				departmentEntity.setAddName("自动添加");
				departmentService.save(departmentEntity);
			}
		}
		return "ok";
	}
	
}
