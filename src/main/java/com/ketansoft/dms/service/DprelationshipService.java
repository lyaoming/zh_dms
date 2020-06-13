package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.DprelationshipEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-19 16:41:52
 */
public interface DprelationshipService {
	
	DprelationshipEntity queryObject(Integer pdId);
	
	List<DprelationshipEntity> queryList(Map<String, Object> map);

	List<DprelationshipEntity> queryRecordList(Map<String, Object> map);

	List<DprelationshipEntity> queryRetreateList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);

	int queryRecordTotal(Map<String,Object>map);
	
	void save(DprelationshipEntity dprelationship);

	void saveBatch(List<DprelationshipEntity> dprelationshipList);

	void update(DprelationshipEntity dprelationship);

	void leave(Map<String, Object> map);

	List<DprelationshipEntity> queryObjectStatus(Map<String, Object> map);

	List<DprelationshipEntity> querySameList(Map<String, Object> map);

	List<DprelationshipEntity> findPersonnel(Map<String, Object> map);


	void leaveSelect(Map<String, Object> map);
	
	void delete(Integer pdId);
	
	void deleteBatch(Integer[] pdIds);

	List<DprelationshipEntity>exportSelect(Integer[]pdIds);
}
