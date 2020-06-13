package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.UseUnitEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-05 15:26:38
 */
public interface UseUnitService {
	
	UseUnitEntity queryObject(Integer tId);
	
	List<UseUnitEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UseUnitEntity useUnit);

	void saveBatch(List<UseUnitEntity> useUnitList);

	void update(UseUnitEntity useUnit);
	
	void delete(Integer tId);
	
	void deleteBatch(Integer[] tIds);
}
