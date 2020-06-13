package com.ketansoft.dms.controller;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.ketansoft.dms.entity.*;
import com.ketansoft.dms.service.*;
import com.sun.corba.se.impl.orbutil.concurrent.SyncUtil;
import net.sf.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.UnsupportedEncodingException;
import java.text.*;
import java.util.*;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-19 16:41:52
 */
@Controller
@RequestMapping("dprelationship")
public class DprelationshipController {
	@Autowired
	private DprelationshipService dprelationshipService;
	@Autowired
	private  DormService dormService;
	@Autowired
	private CprelationshipService cprelationshipService;
	@Autowired
	private DormRoomService dormRoomService;
	@Autowired
	private ShowDataService showDataService;

	@Autowired
	private RecordService recordService;

	@Autowired
	private PersonnelService personnelService;

	@Autowired
	private MonthService monthService;
	
	@RequestMapping("/dprelationship.html")
	public String list(){
		return "dprelationship/dprelationship.html";
	}

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	String leaveTime=df.format(new Date());

	Long nowDate=new Date().getTime();



	Integer[] bfcost=new Integer[20];

	SimpleDateFormat ydf=new SimpleDateFormat("yyyy");


	int year=Integer.parseInt(ydf.format(new Date()));


	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("dprelationship:list")
	public R list(String dpkeyword,String dpdorm,String sbgTime,String sendTime,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("sbgTime",sbgTime);
        map.put("sendTime",sendTime);
		map.put("keyword",dpkeyword);
        map.put("dorm",dpdorm);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		//查询列表数据
		List<DprelationshipEntity> dprelationshipList = dprelationshipService.queryList(map);
		for(DprelationshipEntity dprelationshipEntity:dprelationshipList){
			Integer rId=dprelationshipEntity.getrId();
				DormRoomEntity dormRoomEntity = dormRoomService.queryObjectG(rId);
				if (dormRoomEntity!= null) {
					dprelationshipEntity.setRoomName(dormRoomEntity.getRoomName());
				}
			Integer status=dprelationshipEntity.getStatus();
			if(status>0) {
				map.put("pId", dprelationshipEntity.getPId());
				List list=cprelationshipService.queryList(map);
				dprelationshipEntity.setCostList(list);
			}
		}
		int total = dprelationshipService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(dprelationshipList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/retreatelist")
	public R retreatelist(String dpkeyword,String dpdorm,String sbgTime,String sendTime,Integer page, Integer limit){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sbgTime",sbgTime);
		map.put("sendTime",sendTime);
		map.put("keyword",dpkeyword);
		map.put("dorm",dpdorm);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		//查询列表数据
		List<DprelationshipEntity> dprelationshipList = dprelationshipService.queryRetreateList(map);
		for(DprelationshipEntity dprelationshipEntity:dprelationshipList){
			Integer rId=dprelationshipEntity.getrId();
			DormRoomEntity dormRoomEntity = dormRoomService.queryObjectG(rId);
			if (dormRoomEntity!= null) {
				dprelationshipEntity.setRoomName(dormRoomEntity.getRoomName());
			}
			Integer status=dprelationshipEntity.getStatus();
			if(status>0) {
				map.put("pId", dprelationshipEntity.getPId());
				List list=cprelationshipService.queryList(map);
				dprelationshipEntity.setCostList(list);
			}
		}
		int total = dprelationshipService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(dprelationshipList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{pdId}")
	@RequiresPermissions("dprelationship:info")
	public R info(@PathVariable("pdId") Integer pdId){
		List res=new ArrayList();
		DprelationshipEntity dprelationship = dprelationshipService.queryObject(pdId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId",dprelationship.getPId());
		map.put("parentId",dprelationship.getDId());
		List<CprelationshipEntity>list=cprelationshipService.queryList(map);
		Integer[] costs=new Integer[list.size()];
		for(int i=0;i<list.size();i++){
			CprelationshipEntity cprelationshipEntity=list.get(i);
			costs[i]=cprelationshipEntity.getId();
		}
        bfcost=costs;
		dprelationship.setCosts(costs);
		List room=dormRoomService.queryList(map);
		if(room.size()>0){
			res.add(dprelationship);
			res.add(room);
		}else{
			res.add(dprelationship);
		}
		return R.ok().put("res", res);
	}

	@ResponseBody
	@RequestMapping("/room/{dId}")
	public R room(@PathVariable("dId") Integer dId){
		Map<String,Object>map=new HashMap<>();
		map.put("parentId",dId);
		List room=dormRoomService.queryList(map);
		return R.ok().put("room",room);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("dprelationship:save")
	public R save(@RequestBody DprelationshipEntity dprelationship) throws Exception{
	    Integer dId=dprelationship.getDId();
		Integer pId=dprelationship.getPId();
		Double setNull=0.00;
		//判断填写的入住时间和到期时间是否合理
		Date d1=dprelationship.getCheckInTime();
		Date d2=dprelationship.getExpectedDueTime();
		if(d1.compareTo(d2)>0){
			return R.error("填写的入住时间不能小于到期时间");
		}
		//判断人员是否已经入住
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId",pId);
		map.put("bgTime",d1);
		DormEntity dormEntity=dormService.queryObject(dId);
		List<DprelationshipEntity>dpList=dprelationshipService.querySameList(map);
		if(dpList.size()>0){ return R.error("所选人员已经入住！"); }
		//判断入住房屋是否还有空房
		Integer allNum=dormEntity.getDAllnum();
		Integer nowNum=dormEntity.getDNum();
		if(dprelationship.getDeposit().intValue()==0){
			dprelationship.setDepositMoney(setNull);
		}
		if(dprelationship.getDeposit().intValue()>0){
			if(dprelationship.getDepositMoney().intValue()<=0)
				return R.error("请填写所交押金的正确金额！");
		}
		//判断选择的是否为一个房屋
		if(dprelationship.getFlag()!=null){
			return R.error("请选择一个房屋舍号");
		}

		if(nowNum<allNum) {

			//写入收费关系
			CprelationshipEntity cprelationshipEntity=new CprelationshipEntity();
			Integer[] costList=dprelationship.getCosts();
			if(costList!=null){
				for(int i=0;i<costList.length;i++){
					setNew(costList[i],dprelationship.getPId(),d1,dprelationship.getExpectedDueTime());
				}
			}
			//写入收费记录
			if(record(dprelationship.getPId(),dprelationship.getdAddress(),dprelationship.getCheckInTime())=="ok") {

				//写入入住关系
				dprelationshipService.save(dprelationship);

				//写入房屋房间人员ID

				DormRoomEntity dormRoomSame = dormRoomService.queryObject(dprelationship.getrId());
				DormRoomEntity dormRoomEntity = new DormRoomEntity();
				dormRoomEntity.setrId(dprelationship.getrId());
				dormRoomEntity.setPId(dprelationship.getPId());
				dormRoomEntity.setStatus(1);
				if (dId.intValue() > 0) {
					dormService.add(dId);
				}
				dormRoomService.update(dormRoomEntity);
				map.put("entryNum", 1);
				showDataService.add(map);
				return R.ok();
			}else {
				return R.error();
			}
		}
		return  R.error("房屋人数已满无法再增加人入住！");
	}

	@ResponseBody
	@RequestMapping("/continute")
	public R continute(@RequestBody DprelationshipEntity dprelationship){
		dprelationshipService.update(dprelationship);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("dprelationship:update")
	public R update(@RequestBody DprelationshipEntity dprelationship) throws Exception{
		//判断填写的时间是否合理
		Date d1=dprelationship.getCheckInTime();
		Date d2=dprelationship.getExpectedDueTime();
		if(d1.compareTo(d2)>0){
			return R.error("填写的入住时间不能小于到期时间");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Date updatebgTime=dprelationship.getCheckInTime();
		Integer dId = dprelationship.getDId();
		Integer pId=dprelationship.getPId();
		Integer bfpId=dprelationship.getBfpId();
		Integer BdId = dprelationship.getBeforeDid();
		Integer rId=dprelationship.getrId();
		Integer bfrId=dprelationship.getBfrId();
		Double setNull=0.00;
		Integer rentreate=dprelationship.getRentreate();
		DormEntity dormEntity=dormService.queryObject(dId);
		Integer allNum=dormEntity.getDAllnum();
		Integer nowNum=dormEntity.getDNum();
		map.put("pId", pId);
		if(bfpId.intValue()!=pId.intValue()){//若换人
			map.put("bgTime", updatebgTime);
			//查询入住人员是否已经入住
			List<DprelationshipEntity> dpList = dprelationshipService.querySameList(map);
			if (dpList.size() > 0) {
				return R.error("该人员已经入住！");
			}
		}
		//换房间
		if(bfrId.intValue()!=rId.intValue()){
			DormRoomEntity dormRoomEntity=new DormRoomEntity();
			DormRoomEntity dormRoomEntity1=dormRoomService.queryObjectG(bfrId);
			DormRoomEntity dormRoomEntity2=dormRoomService.queryObjectG(rId);
			dormRoomEntity.setPId(null);
			dormRoomEntity.setrId(bfrId);
			if(dormRoomEntity1!=null) {
				if (dormRoomEntity1.getRoomName().equals("全套")) {
					dormRoomEntity.setStatus(0);
				} else {
					dormRoomEntity.setStatus(1);
				}
				if(dormRoomEntity2.getRoomName().equals("全套")){
					dormRoomEntity.setStatus(0);
				}
			}
			dormRoomService.update(dormRoomEntity);
			dormRoomEntity.setPId(dprelationship.getPId());
			dormRoomEntity.setrId(rId);
			dormRoomEntity.setStatus(1);
			dormRoomService.update(dormRoomEntity);
		}
		if(dprelationship.getDeposit().intValue()==0)
		{
			dprelationship.setDepositMoney(setNull);
		}
		if(dprelationship.getDeposit().intValue()>0)
		{
			if(dprelationship.getDepositMoney().intValue()<=0)
				return R.error("请填写所交押金的正确金额！");
		}
		if(dprelationship.getFlag()!=null){
		    return R.error("请选择一个房屋号");
		}
		if(BdId.intValue()!=dId.intValue()) {
		    if(BdId==null){
		        BdId=dId;
		    }
		    dormService.reduce(BdId);
		}
		if(nowNum<allNum){
		    if(rentreate==null&&BdId.intValue()!=dId.intValue())dormService.add(dId);
		}
		dprelationshipService.update(dprelationship);
		if(nowNum>allNum)return R.error("修改的房屋人数出现问题！");

		Integer[] costList=dprelationship.getCosts();
		if(costList!=null&&costList.length>0){
			for(int j=0;j<bfcost.length;j++){
				setNull(bfcost[j]);
			}
			for(int i=0;i<costList.length;i++){
				setNew(costList[i],dprelationship.getPId(),d1,dprelationship.getExpectedDueTime());
			}
		}else if(costList==null||costList.length==0&&bfcost.length!=0){
			for(int i=0;i<bfcost.length;i++){
				setNull(bfcost[i]);
			}
		}

		//写入收费记录
		if(record(dprelationship.getPId(),dprelationship.getdAddress(),dprelationship.getCheckInTime())=="ok"){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] pdIds){
		dprelationshipService.deleteBatch(pdIds);
		return R.ok();
	}

	@ResponseBody
	@RequestMapping("leave")
	public R leave(@RequestBody Integer[] pIds){
		Map<String, Object>map = new HashMap<String, Object>();
		for(int i=0 ;i<pIds.length;i++) {
			DormRoomEntity dormRoomEntity1 = dormRoomService.queryObject(pIds[i]);
			DormRoomEntity dormRoomEntity2=new DormRoomEntity();
			dormRoomEntity2.setPId(null);
			if(dormRoomEntity1!=null) {
				dormRoomEntity2.setrId(dormRoomEntity1.getrId());
				dormRoomEntity2.setStatus(1);
				dormRoomService.update(dormRoomEntity2);
			}
		}
		int len=pIds.length;
		map.put("pIds",pIds);
		map.put("len",len);
		System.out.println(len);
		map.put("leaveTime",leaveTime);
		dprelationshipService.leave(map);
		cprelationshipService.leave(map);
		for(int i=0;i<pIds.length;i++) {
			map.put("retreateNum", 1);
			showDataService.add(map);
		}
		return R.ok();
	}

	@ResponseBody
	@RequestMapping("leaveSelect")
	public R leaveBatch(@RequestBody Integer[] pdIds){
		Map<String,Object>map=new HashMap<>();
		map.put("leaveTime",leaveTime);
		map.put("pdIds",pdIds);
		List<DprelationshipEntity>statusList=dprelationshipService.queryObjectStatus(map);
		if(statusList.size()>0){
			return R.error("已退租人员请勿重复退租");
		}
		for(int i=0;i<pdIds.length;i++) {
			map.put("pdId",pdIds[i]);
			dprelationshipService.leaveSelect(map);
		}
		cprelationshipService.leaveSelect(map);
		for(int i=0;i<pdIds.length;i++){
			DprelationshipEntity dprelationshipEntity=dprelationshipService.queryObject(pdIds[i]);
			DormRoomEntity dormRoomEntity=dormRoomService.queryObjectG(dprelationshipEntity.getrId());
			map.put("retreateNum", 1);
			showDataService.add(map);
			if(dormRoomEntity!=null) {
				dormRoomEntity.setrId(dprelationshipEntity.getrId());
				dormRoomEntity.setPId(null);
				dormRoomEntity.setStatus(1);
				dormRoomService.update(dormRoomEntity);
			}
		}
		return R.ok();
	}

	public String setNull(Integer id){
		CprelationshipEntity cprelationshipEntity=new CprelationshipEntity();
		cprelationshipEntity.setId(id);
		cprelationshipEntity.setPId(null);
		cprelationshipEntity.setFlag(1);
		cprelationshipEntity.setBgTime(null);
		cprelationshipEntity.setEndTime(null);
		cprelationshipEntity.setStatus(0);
		cprelationshipService.update(cprelationshipEntity);
		return "ok";
	}

	public String setNew(Integer id,Integer pId,Date bgtime,Date endtime){
		CprelationshipEntity cprelationshipEntity=new CprelationshipEntity();
		cprelationshipEntity.setId(id);
		cprelationshipEntity.setPId(pId);
		cprelationshipEntity.setBgTime(bgtime);
		cprelationshipEntity.setEndTime(endtime);
		cprelationshipEntity.setStatus(1);
		cprelationshipService.update(cprelationshipEntity);
		return "ok";
	}

	public String record(Integer pId,String address,Date checkInTime)throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pId",pId);
		//写入收费记录
		RecordEntity recordEntity=new RecordEntity();

		List<MonthEntity>month=monthService.queryList(map);



		//判断写入记录的月份是自定义的哪个月
		for(int i=0;i<month.size();i++){
			MonthEntity monthEntity=month.get(i);
			Date edTime = df.parse(monthEntity.getMEndtime());
			Long checkTime = checkInTime.getTime();
			Long end=edTime.getTime();
			if(checkTime<end){
				recordEntity.setRecordMonth(monthEntity.getMName());
				recordEntity.setRecordYear(monthEntity.getYName());
				map.put("month",monthEntity.getMName());
				map.put("year",monthEntity.getYName());
				break;
			}
		}
		List<CostItemEntity> costs=recordService.costList(map);

		JSONArray array = JSONArray.fromObject(costs);

		PersonnelEntity personnelEntity=personnelService.queryObject(pId);
		if(personnelEntity!=null){
			recordEntity.setPId(personnelEntity.getPId());
			recordEntity.setPName(personnelEntity.getPName());
			recordEntity.setPDepartment(personnelEntity.getPDepartment());
			recordEntity.setPCategroy(personnelEntity.getClassion());
		}
		recordEntity.setDorm(address);
		recordEntity.setCostItems(array.toString());
		recordEntity.setStatus(1);
		List same=recordService.findSame(map);
		if(same!=null&&same.size()>0){
			recordService.update(recordEntity);
		}else {
			recordService.save(recordEntity);
		}
		return "ok";
	}
}
