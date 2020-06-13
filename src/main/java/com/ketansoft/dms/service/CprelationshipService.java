package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.CprelationshipEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-28 09:04:22
 */
public interface CprelationshipService {
	
	CprelationshipEntity queryObject(Integer pId);
	
	List<CprelationshipEntity> queryList(Map<String, Object> map);

	List<CprelationshipEntity> edqueryList(Map<String, Object> map);

	List<CprelationshipEntity> queryRecordList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CprelationshipEntity cprelationship);

	void saveBatch(List<CprelationshipEntity> cprelationshipList);

	void update(CprelationshipEntity cprelationship);

	void leave(Map<String,Object>map);

	void leaveSelect(Map<String,Object>map);
	
	void delete(Integer pId);
	
	void deleteBatch(Integer[] pIds);
}
