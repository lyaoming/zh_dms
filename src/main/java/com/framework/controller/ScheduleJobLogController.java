package com.framework.controller;

import com.framework.entity.ScheduleJobLogEntity;
import com.framework.service.ScheduleJobLogService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年12月1日 下午10:39:52
 */
//@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
//	@Autowired
	private ScheduleJobLogService scheduleJobLogService;

	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public R list(Integer page, Integer limit, Integer jobId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jobId", jobId);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		// 查询列表数据
		List<ScheduleJobLogEntity> jobList = scheduleJobLogService.queryList(map);
		int total = scheduleJobLogService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(jobList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public R info(@PathVariable("logId") Long logId) {
		ScheduleJobLogEntity log = scheduleJobLogService.queryObject(logId);

		return R.ok().put("log", log);
	}
}
