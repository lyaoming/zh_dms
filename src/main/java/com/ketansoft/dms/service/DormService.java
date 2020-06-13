package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.DormEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-17 13:40:46
 */
public interface DormService {
	
	DormEntity queryObject(Integer dId);
	
	List<DormEntity> queryList(Map<String, Object> map);

	List<DormEntity> queryUseAdmin(Map<String, Object> map);

	Integer queryObjectTotal(Integer dId);
	
	int queryTotal(Map<String, Object> map);

	int queryUseAdminTotal(Map<String,Object>map);
	
	void save(DormEntity dorm);

	void saveBatch(List<DormEntity> dormList);

	void update(DormEntity dorm);

	void add(Integer dId);

	void reduce(Integer dId);
	
	void delete(Integer dId);

	void deletep(Integer pId);

	void deletec(Integer[] cIds);
	
	void deleteBatch(Integer[] dIds);
}
