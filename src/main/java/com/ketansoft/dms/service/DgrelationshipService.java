package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.DgrelationshipEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-22 16:38:25
 */
public interface DgrelationshipService {
	
	DgrelationshipEntity queryObject(Integer gdId);
	
	List<DgrelationshipEntity> queryList(Map<String, Object> map);

	List<DgrelationshipEntity> querySame(Map<String, Object> map);

	List<DgrelationshipEntity> peizhiList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DgrelationshipEntity dgrelationship);

	void saveBatch(List<DgrelationshipEntity> dgrelationshipList);

	void update(DgrelationshipEntity dgrelationship);
	
	void delete(Integer gdId);
	
	void deleteBatch(Integer[] gdIds);
}
