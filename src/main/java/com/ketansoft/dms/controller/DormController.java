package com.ketansoft.dms.controller;

import com.framework.utils.R;
import com.ketansoft.dms.entity.*;
import com.ketansoft.dms.service.*;
import org.apache.ibatis.logging.LogException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-17 13:40:46
 */
@Controller
@RequestMapping("dorm")
public class DormController {
	@Autowired
	private DormService dormService;

	@Autowired
	private PersonnelService personnelService;

	@Autowired
	private DormRoomService dormRoomService;

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private UseAdminService useAdminService;

	@Autowired
	private UseUnitService useUnitService;
	
	@RequestMapping("/dorm.html")
	public String list(){
		return "dorm/dorm.html";
	}
	
	@RequestMapping("/dorm_add.html")
	public String add(){
		return "dorm/dorm_add.html";
	}

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	Date addTime=new Date();


	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		//查询列表数据
		List<DormEntity> dormList = dormService.queryList(map);

		return R.ok().put("dormList",dormList);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{dId}")
	public R info(@PathVariable("dId") Integer dId){
		Map<String,Object>map=new HashMap<>();
		List res=new ArrayList();
		map.put("parentId",dId);
		DormEntity dorm = dormService.queryObject(dId);
		UseUnitEntity useUnitEntity=useUnitService.queryObject(dorm.gettId());
		UseAdminEntity useAdminEntity=useAdminService.queryObject(dorm.getuId());
		if(useUnitEntity!=null){
			dorm.setUseUnit(useUnitEntity.getUseUnit());
		}
		if(useAdminEntity!=null){
			dorm.setUseAdmin(useAdminEntity.getUseAdmin());
		}
		List<DormRoomEntity>dormRoom=dormRoomService.queryList(map);
		res.add(dorm);
		if(dormRoom.size()>0){
			res.add(dormRoom);
			return R.ok().put("res",res);
		}
		return R.ok().put("res",res);
	}
	
	/**
	 * 保存
	 */

	private static Logger logger = Logger.getLogger(LogException.class.getName());
	@ResponseBody
	@RequestMapping("/save")
	public R save(@RequestBody DormEntity dorm){
		Map<String,Object>map=new HashMap<>();
		DormRoomEntity dormRoomEntity = new DormRoomEntity();
//		判断是否拆分设置宿舍容纳人数
		if(dorm.getDType()!=null) {
			if (dorm.getDType().intValue()== 2) {
				if (dorm.getDSpecification().intValue() == 1) {
					dorm.setDAllnum(2);
				} else if (dorm.getDSpecification().intValue()== 2) {
					dorm.setDAllnum(3);
				}else if(dorm.getDSpecification().intValue()==3){
					dorm.setDAllnum(4);
				}
			}
		}
		if(dorm.getDAllnum()==null){
			dorm.setDAllnum(0);
		}
		if(dorm.getDNum()==null){
			dorm.setDNum(0);
		}
		if (dorm.getDType()!=null&&dorm.getDType().intValue()== 1) {
			dorm.setDAllnum(1);
		}
		try {
			dormService.save(dorm);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "错误", e);
			return R.error("失败");
		}
		if(dorm.getDType()!=null) {
			dormRoomEntity.setParentId(dorm.getDId());
			dormRoomEntity.setRoomName("全套");
			dormRoomEntity.setRoomAera(dorm.getDArea());
			if(dorm.getDAllnum().intValue()==1){
				dormRoomEntity.setStatus(1);
			}else{
				dormRoomEntity.setStatus(0);
			}
			dormRoomService.save(dormRoomEntity);
		}
		return R.ok().put("dId",dorm.getDId());
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	public R update(@RequestBody DormEntity dorm){
		Map<String,Object>map=new HashMap<>();
		map.put("parentId",dorm.getDId());
		List<DormRoomEntity> roomList=dormRoomService.updateList(map);
		Integer dType=dorm.getDType();
		//bfdType为前规格
		Integer bfdType=dorm.getBfdType();
		Integer nowType=dorm.getDSpecification();
		if(dorm.getDAllnum()==null){
			dorm.setDAllnum(0);
		}
		if(bfdType==null){
			bfdType=0;
		}
		if(dorm.getDNum()==null){
			dorm.setDNum(0);
		}

        //		判断是否拆分设置宿舍容纳人数
		if(dorm.getDType()!=null) {
			if (dorm.getDType().intValue()== 2) {
				for(int i=0;i<roomList.size();i++){
					DormRoomEntity dormRoomEntity=roomList.get(i);
					if(dormRoomEntity.getRoomName().equals("全套")){
						dormRoomEntity.setStatus(0);
					}else{
						dormRoomEntity.setStatus(1);
					}
					dormRoomService.update(dormRoomEntity);
				}
				if (dorm.getDSpecification().intValue()== 1) {
					dorm.setDAllnum(2);
				} else if (dorm.getDSpecification().intValue()== 2) {
					dorm.setDAllnum(3);
				}else if(dorm.getDSpecification().intValue()==3){
					dorm.setDAllnum(4);
				}
			}
			if (dorm.getDType().intValue()== 1) {
				dorm.setDAllnum(1);
				if(dorm.getDNum().intValue()>0&&dorm.getDNum().intValue()>dorm.getDAllnum().intValue()){
					return R.error("房屋类型转换失败，请检查现住人数是否有问题！");
				}

				for(int i=0;i<roomList.size();i++){
					DormRoomEntity dormRoomEntity=roomList.get(i);
					if(dormRoomEntity.getRoomName().equals("全套")){
						dormRoomEntity.setStatus(1);
					}else{
						dormRoomEntity.setStatus(0);
					}
					dormRoomService.update(dormRoomEntity);
				}
			}
		}
		if(nowType.intValue()!=bfdType.intValue()){
			dormRoomService.delete(dorm.getDId());
			DormRoomEntity dormRoomEntity1=new DormRoomEntity();
			dormRoomEntity1.setParentId(dorm.getDId());
			dormRoomEntity1.setRoomName("全套");
			dormRoomEntity1.setRoomAera(dorm.getDArea());
			if(dorm.getDType().intValue()==1){
				dormRoomEntity1.setStatus(1);
			}else{
				dormRoomEntity1.setStatus(0);
			}
			dormRoomService.save(dormRoomEntity1);
			if(nowType==1){
				for(int i=0;i<2;i++){
					DormRoomEntity dormRoomEntity=new DormRoomEntity();
					if(dorm.getDType().intValue()==1){
						dormRoomEntity.setStatus(0);
					}else{
						dormRoomEntity.setStatus(1);
					}
					dormRoomEntity.setRoomAera(0.00);
					dormRoomEntity.setParentId(dorm.getDId());
					if(i==0) {
						dormRoomEntity.setRoomName("A房");
					}
					if(i==1){
						dormRoomEntity.setRoomName("B房");
					}
					dormRoomService.save(dormRoomEntity);
				}
			}
			if(nowType==2){
				for(int i=0;i<3;i++){
					DormRoomEntity dormRoomEntity=new DormRoomEntity();
					if(dorm.getDType().intValue()==1){
						dormRoomEntity.setStatus(0);
					}else{
						dormRoomEntity.setStatus(1);
					}
					dormRoomEntity.setRoomAera(0.00);
					dormRoomEntity.setParentId(dorm.getDId());
					if(i==0) {
						dormRoomEntity.setRoomName("A房");
					}
					if(i==1){
						dormRoomEntity.setRoomName("B房");
					}
					if(i==2){
						dormRoomEntity.setRoomName("C房");
					}
					dormRoomService.save(dormRoomEntity);
				}
			}
			if(nowType==3){
				for(int i=0;i<4;i++){
					DormRoomEntity dormRoomEntity=new DormRoomEntity();
					if(dorm.getDType().intValue()==1){
						dormRoomEntity.setStatus(0);
					}else{
						dormRoomEntity.setStatus(1);
					}
					dormRoomEntity.setRoomAera(0.00);
					dormRoomEntity.setParentId(dorm.getDId());
					if(i==0) {
						dormRoomEntity.setRoomName("A房");
					}
					if(i==1){
						dormRoomEntity.setRoomName("B房");
					}
					if(i==2){
						dormRoomEntity.setRoomName("C房");
					}
					if(i==3){
						dormRoomEntity.setRoomName("D房");
					}
					dormRoomService.save(dormRoomEntity);
				}
			}
		}

		dormService.update(dorm);
		return R.ok().put("dId",dorm.getDId());
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete/{dId}")
	public R delete(@PathVariable("dId") Integer dId){
		dormRoomService.delete(dId);
		dormService.delete(dId);
		return R.ok();
	}

	@ResponseBody
	@RequestMapping("/deleteBatch")
	public R deleteBatch(@RequestBody Integer[]dIds){
		dormService.deleteBatch(dIds);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/personnel/{dId}")
	public R find(@PathVariable("dId") Integer dId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PersonnelEntity> personnel=new ArrayList<>();
		map.put("dId",dId);
		try {
			personnel= personnelService.queryNowList(map);
			for (int i = 0; i < personnel.size(); i++) {
				PersonnelEntity personnelEntity = personnel.get(i);
				DormRoomEntity dormRoomEntity = dormRoomService.queryObject(personnelEntity.getPId());
				if (dormRoomEntity != null) {
					personnelEntity.setRoomName(dormRoomEntity.getRoomName());
				} else {
					personnelEntity.setRoomName("全套");
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, "错误", e);
			return R.error("失败");
		}
		return R.ok().put("personnel",personnel);
	}

	public  String addDpm(String dpm){
		Map<String,Object>map=new HashMap<>();
		//新增部室
		if(!(dpm.isEmpty())&&dpm!=null){
			map.put("dpmName", dpm);
		List<DepartmentEntity>sameList=departmentService.querySameName(map);
		if(sameList.size()==0){
			DepartmentEntity departmentEntity=new DepartmentEntity();
			departmentEntity.setDpmName(dpm);
			departmentEntity.setAddTime(addTime);
			departmentEntity.setAddName("自动添加");
			departmentService.save(departmentEntity);
		  }
		}
		return "ok";
	}
}
