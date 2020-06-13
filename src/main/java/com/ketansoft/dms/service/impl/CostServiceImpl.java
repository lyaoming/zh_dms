package com.ketansoft.dms.service.impl;

import com.ketansoft.dms.entity.ExpensetableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.CostDao;
import com.ketansoft.dms.entity.CostEntity;
import com.ketansoft.dms.service.CostService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-07-26 10:31:20
 */
@Service("costService")
public class CostServiceImpl implements CostService {
	@Autowired
	private CostDao costDao;
	
	@Override
	public CostEntity queryObject(Integer cId){
		return costDao.queryObject(cId);
	}
	
	@Override
	public List<CostEntity> queryList(Map<String, Object> map){
		return costDao.queryList(map);
	}

	@Override
	public  List<ExpensetableEntity>queryRecordList(Map<String,Object>map){return costDao.queryRecordList(map);};
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return costDao.queryTotal(map);
	}
	
	@Override
	public void save(CostEntity cost){
		costDao.save(cost);
	}

	@Override
	public void saveBatch(List<CostEntity> costList){
		costDao.saveBatch(costList);
	}

	@Override
	public void update(CostEntity cost){
		costDao.update(cost);
	}
	
	@Override
	public void delete(Integer cId){
		costDao.delete(cId);
	}
	
	@Override
	public void deleteBatch(Integer[] cIds){
		costDao.deleteBatch(cIds);
	}
	
}
