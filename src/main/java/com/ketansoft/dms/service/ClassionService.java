package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.ClassionEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 09:25:50
 */
public interface ClassionService {
	
	ClassionEntity queryObject(Integer id);
	
	List<ClassionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassionEntity classion);

	void saveBatch(List<ClassionEntity> classionList);

	void update(ClassionEntity classion);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
