package com.ketansoft.dms.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ketansoft.dms.entity.DormDetailedEntity;
import com.ketansoft.dms.entity.DormEntity;
import com.ketansoft.dms.service.DormService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.ketansoft.dms.entity.DormRoomEntity;
import com.ketansoft.dms.service.DormRoomService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-30 13:44:30
 */
@Controller
@RequestMapping("dormroom")
public class DormRoomController {
	@Autowired
	private DormRoomService dormRoomService;

	@Autowired
	private DormService dormService;
	
	@RequestMapping("/dormroom.html")
	public String list(){
		return "dormroom/dormroom.html";
	}
	
	@RequestMapping("/dormroom_add.html")
	public String add(){
		return "dormroom/dormroom_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("dormroom:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		if(page!=null&&limit!=null) {
			map.put("offset", (page - 1) * limit);
			map.put("limit", limit);
		}
		
		//查询列表数据
		List<DormRoomEntity> dormRoomList = dormRoomService.queryList(map);
		if(page!=null&&limit!=null) {
			int total = dormRoomService.queryTotal(map);

			PageUtils pageUtil = new PageUtils(dormRoomList, total, limit, page);

			return R.ok().put("page", pageUtil);
		}
		return R.ok().put("dormRoomList",dormRoomList);
	}


	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{rId}")
	public R info(@PathVariable("rId") Integer rId){
		DormRoomEntity dormRoom = dormRoomService.queryObjectG(rId);
		return R.ok().put("dormRoom", dormRoom);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	public R save(@RequestBody List<String> room){
		List res=new ArrayList();
		for(int i=0;i<room.size();i++) {
			DormRoomEntity dormRoom=new DormRoomEntity();
			JSONObject object = JSONObject.parseObject(room.get(i).toString());
			dormRoom.setParentId(object.getInteger("parentId"));
			dormRoom.setRoomName(object.getString("roomName"));
			DormEntity dormEntity=dormService.queryObject(object.getInteger("parentId"));
			if(dormEntity.getDType().intValue()==1) {
				dormRoom.setStatus(0);
			}else{
				dormRoom.setStatus(1);
			}
			if(object.getDouble("roomAera")!=null) {
				dormRoom.setRoomAera(object.getDouble("roomAera"));
			}else{
				dormRoom.setRoomAera(0.00);
			}
			dormRoomService.save(dormRoom);
			res.add(dormRoom.getrId());
		}
		
		return R.ok();
	}

	@ResponseBody
	@RequestMapping("/update")
	public R edsave(@RequestBody DormRoomEntity dormRoom){
		dormRoomService.update( dormRoom);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("dormroom:delete")
	public R delete(@RequestBody Integer[] nodeIds){
		dormRoomService.deleteBatch(nodeIds);
		
		return R.ok();
	}
	
}
