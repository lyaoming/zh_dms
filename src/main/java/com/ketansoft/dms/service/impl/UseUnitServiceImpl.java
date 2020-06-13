package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.UseUnitDao;
import com.ketansoft.dms.entity.UseUnitEntity;
import com.ketansoft.dms.service.UseUnitService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-05 15:26:38
 */
@Service("useUnitService")
public class UseUnitServiceImpl implements UseUnitService {
	@Autowired
	private UseUnitDao useUnitDao;
	
	@Override
	public UseUnitEntity queryObject(Integer tId){
		return useUnitDao.queryObject(tId);
	}
	
	@Override
	public List<UseUnitEntity> queryList(Map<String, Object> map){
		return useUnitDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return useUnitDao.queryTotal(map);
	}
	
	@Override
	public void save(UseUnitEntity useUnit){
		useUnitDao.save(useUnit);
	}

	@Override
	public void saveBatch(List<UseUnitEntity> useUnitList){
		useUnitDao.saveBatch(useUnitList);
	}

	@Override
	public void update(UseUnitEntity useUnit){
		useUnitDao.update(useUnit);
	}
	
	@Override
	public void delete(Integer tId){
		useUnitDao.delete(tId);
	}
	
	@Override
	public void deleteBatch(Integer[] tIds){
		useUnitDao.deleteBatch(tIds);
	}
	
}
