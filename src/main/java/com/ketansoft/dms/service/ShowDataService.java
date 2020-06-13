package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.ShowDataEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-03 17:20:48
 */
public interface ShowDataService {

	ShowDataEntity queryObject(Integer id);

	List<ShowDataEntity> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(ShowDataEntity showData);

	void saveBatch(List<ShowDataEntity> showDataList);

	void update(ShowDataEntity showData);

	void delete(Integer id);

	void deleteBatch(Integer[] ids);

	void add(Map<String,Object>map);

	void reduce(Map<String,Object>map);
}
