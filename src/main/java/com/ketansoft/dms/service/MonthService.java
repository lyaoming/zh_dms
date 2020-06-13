package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.MonthEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-08 13:36:25
 */
public interface MonthService {
	
	MonthEntity queryObject(Integer mId);

	List<MonthEntity>queryByYear(Map<String,Object>map);
	
	List<MonthEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MonthEntity month);

	void saveBatch(List<MonthEntity> monthList);

	void update(MonthEntity month);
	
	void delete(Integer mId);
	
	void deleteBatch(Integer[] mIds);
}
