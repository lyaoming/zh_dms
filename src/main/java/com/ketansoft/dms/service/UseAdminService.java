package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.UseAdminEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-05 09:48:57
 */
public interface UseAdminService {
	
	UseAdminEntity queryObject(Integer uId);
	
	List<UseAdminEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UseAdminEntity useAdmin);

	void saveBatch(List<UseAdminEntity> useAdminList);

	void update(UseAdminEntity useAdmin);
	
	void delete(Integer uId);
	
	void deleteBatch(Integer[] uIds);
}
