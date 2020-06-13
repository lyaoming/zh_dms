package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.CostItemEntity;
import com.ketansoft.dms.entity.CprelationshipEntity;
import com.ketansoft.dms.entity.RecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-08 17:27:52
 */
public interface RecordService {
	
	RecordEntity queryObject(Integer recordId);
	
	List<RecordEntity> queryList(Map<String, Object> map);

	List<RecordEntity> findSame(Map<String, Object> map);

	List<CostItemEntity>costList(Map<String,Object>map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RecordEntity record);

	void saveBatch(List<RecordEntity> recordList);

	void update(RecordEntity record);
	
	void delete(Integer recordId);
	
	void deleteBatch(Integer[] recordIds);
}
