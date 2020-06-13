package com.ketansoft.dms.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ketansoft.dms.entity.DormRoomEntity;
import com.ketansoft.dms.service.DormRoomService;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.ketansoft.dms.service.GoodsService;
import com.ketansoft.dms.entity.DgrelationshipEntity;
import com.ketansoft.dms.service.DgrelationshipService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-22 16:38:25
 */
@Controller
@RequestMapping("dgrelationship")
public class DgrelationshipController {
	@Autowired
	private DgrelationshipService dgrelationshipService;

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private DormRoomService dormRoomService;
	
	@RequestMapping("/dgrelationship.html")
	public String list(){
		return "dgrelationship/dgrelationship.html";
	}

	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("dgrelationship:list")
	public R list(Integer dId,Integer page,Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("dId",dId);

		map.put("offset", (page - 1) * limit);

		map.put("limit", limit);
		
		//查询列表数据
		List<DgrelationshipEntity> dgrelationshipList = dgrelationshipService.queryList(map);

		for(int i=0;i<dgrelationshipList.size();i++){
			DgrelationshipEntity dgrelationshipEntity=dgrelationshipList.get(i);
			Integer rId=dgrelationshipEntity.getrId();
			DormRoomEntity dormRoomEntity=dormRoomService.queryObjectG(rId);
			if(dormRoomEntity!=null) {
				dgrelationshipEntity.setRoomName(dormRoomEntity.getRoomName());
			}
		}

		int total = dgrelationshipService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(dgrelationshipList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{gdId}")
	@RequiresPermissions("dgrelationship:info")
	public R info(@PathVariable("gdId") Integer gdId){
		List res=new ArrayList();
		Map<String,Object>map=new HashMap<>();
		DgrelationshipEntity dgrelationship = dgrelationshipService.queryObject(gdId);
		map.put("parentId",dgrelationship.getDId());
		List roomList=dormRoomService.queryList(map);
		res.add(dgrelationship);
		res.add(roomList);
		return R.ok().put("res",res);
	}

	/**
	 *
	 * 房间信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/room/{dId}")
	public R room(@PathVariable("dId") Integer dId){
		Map<String,Object>map=new HashMap<>();
		map.put("parentId",dId);
		List room=dormRoomService.updateList(map);
		return R.ok().put("room",room);
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("dgrelationship:save")
	public R save(@RequestBody DgrelationshipEntity dgrelationship){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer dId=dgrelationship.getDId();
		Integer rId=dgrelationship.getrId();
		if(rId==null){
			dgrelationship.setrId(0);
			rId=0;
		}
		Integer[] gIds=dgrelationship.getgIds();
		for(int i=0;i<gIds.length;i++) {
			map.put("gId", gIds[i]);
			map.put("dId", dId);
			map.put("rId", rId);
			List<DgrelationshipEntity> sameList = dgrelationshipService.querySame(map);
			if (sameList.size() > 0) {
				return R.error("有物品已存在，无需新增！");
			}
			dgrelationship.setGId(gIds[i]);
			dgrelationship.setGdNumber(1);
			dgrelationshipService.save(dgrelationship);
		}
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("dgrelationship:update")
	public R update(@RequestBody DgrelationshipEntity dgrelationship){
		Integer gId=dgrelationship.getGId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("gId",gId);
		dgrelationshipService.update(dgrelationship);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("dgrelationship:delete")
	public R delete(@RequestBody Integer[] gdIds){
		dgrelationshipService.deleteBatch(gdIds);
		return R.ok();
	}
	
}
