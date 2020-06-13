package com.ketansoft.dms.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.ketansoft.dms.entity.*;
import com.ketansoft.dms.service.DprelationshipService;
import com.ketansoft.dms.service.MonthService;
import com.ketansoft.dms.service.RecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.ketansoft.dms.service.ShowDataService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-03 17:20:48
 */
@Controller
@RequestMapping("update")
public class UpdateDataController {
	@Autowired
	private ShowDataService showDataService;

	@Autowired
	private DprelationshipService dprelationshipService;

	@Autowired
	private MonthService monthService;

	@Autowired
	private RecordService recordService;


	Long nowDate = new Date().getTime();

	SimpleDateFormat ydf = new SimpleDateFormat("yyyy");

	SimpleDateFormat mdf = new SimpleDateFormat("MM");

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	int nowYear = Integer.parseInt(ydf.format(new Date()));

	int nowMonth = 0;



	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();


		List<MonthEntity> month = monthService.queryList(map);

		//判断写入记录的月份是自定义的哪个月
		for (int i = 0; i < month.size(); i++) {
			MonthEntity monthEntity = month.get(i);
			Long bg = df.parse(monthEntity.getMBgtime()).getTime();
			Long end = df.parse(monthEntity.getMEndtime()).getTime();
			if ((nowDate - bg > 0) && (end - nowDate + 86400000 > 0)) {
				nowMonth = monthEntity.getMName();
			}
		}
		List<DprelationshipEntity> dprelationshipList = dprelationshipService.queryList(map);
		for (int i = 0; i < dprelationshipList.size(); i++) {

			DprelationshipEntity dprelationshipEntity = dprelationshipList.get(i);
			//自动增加收费记录
			map.put("pId", dprelationshipEntity.getPId());
			int checkInMonth = Integer.parseInt(mdf.format(dprelationshipEntity.getCheckInTime()));
			int checkInYear = Integer.parseInt(ydf.format(dprelationshipEntity.getCheckInTime()));
			map.put("month",nowMonth);
			map.put("year",nowYear);
			List<RecordEntity> same = recordService.findSame(map);
			if (same == null || same.size() == 0) {
				while (checkInYear!=nowYear&&checkInMonth!=nowMonth){
					if(checkInMonth<=12) {
						map.put("month",checkInMonth++);
						map.put("year",checkInYear);
					}else {
						checkInMonth=1;
						map.put("month",checkInMonth++);
						map.put("year",++checkInYear);
					}
					if(checkInMonth==13){
						checkInMonth = 1;
						checkInYear=checkInYear+1;
					}
					List<RecordEntity>same2 =  recordService.findSame(map);
					if (same2 != null && same2.size() > 0) {
						for (int j = 0; j < same2.size(); j++) {
							RecordEntity recordEntity1 = same2.get(j);
							RecordEntity recordEntity2 = new RecordEntity();
						    recordEntity2.setRecordYear(checkInYear);
							recordEntity2.setPName(recordEntity1.getPName());
							recordEntity2.setPDepartment(recordEntity1.getPDepartment());
							recordEntity2.setPCategroy(recordEntity1.getPCategroy());
							recordEntity2.setDorm(recordEntity1.getDorm());
							recordEntity2.setCostItems(recordEntity1.getCostItems());
							recordEntity2.setPId(recordEntity1.getPId());
							recordEntity2.setStatus(recordEntity1.getStatus());
							recordEntity2.setRecordId(null);
							recordEntity2.setRecordMonth(checkInMonth);
							recordService.save(recordEntity2);
						}
					}
				}
			}

		}
		return R.ok();
	}
}
