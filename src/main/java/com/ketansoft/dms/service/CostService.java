package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.CostEntity;
import com.ketansoft.dms.entity.ExpensetableEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-26 10:31:20
 */
public interface CostService {
	
	CostEntity queryObject(Integer cId);
	
	List<CostEntity> queryList(Map<String, Object> map);

	List<ExpensetableEntity>queryRecordList(Map<String,Object>map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CostEntity cost);

	void saveBatch(List<CostEntity> costList);

	void update(CostEntity cost);
	
	void delete(Integer cId);
	
	void deleteBatch(Integer[] cIds);
}
