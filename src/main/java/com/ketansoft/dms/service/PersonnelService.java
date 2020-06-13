package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.PersonnelEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-17 10:08:23
 */
public interface PersonnelService {
	
	PersonnelEntity queryObject(Integer pId);
	
	List<PersonnelEntity> queryList(Map<String, Object> map);

	List<PersonnelEntity> queryNowList(Map<String, Object> map);

	List<PersonnelEntity> querySame(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PersonnelEntity personnel);

	void saveBatch(List<PersonnelEntity> personnelList);

	void update(PersonnelEntity personnel);
	
	void delete(Integer pId);
	
	void deleteBatch(Integer[] pIds);
}
