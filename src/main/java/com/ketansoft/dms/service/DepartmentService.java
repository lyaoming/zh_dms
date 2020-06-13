package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.DepartmentEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-24 19:07:50
 */
public interface DepartmentService {
	
	DepartmentEntity queryObject(Integer dpmId);
	
	List<DepartmentEntity> queryList(Map<String, Object> map);

	List<DepartmentEntity> querySameName(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DepartmentEntity department);

	void saveBatch(List<DepartmentEntity> departmentList);

	void update(DepartmentEntity department);
	
	void delete(Integer dpmId);
	
	void deleteBatch(Integer[] dpmIds);
}
