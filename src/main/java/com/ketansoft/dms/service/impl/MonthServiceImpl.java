package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.MonthDao;
import com.ketansoft.dms.entity.MonthEntity;
import com.ketansoft.dms.service.MonthService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-08 13:36:25
 */
@Service("monthService")
public class MonthServiceImpl implements MonthService {
	@Autowired
	private MonthDao monthDao;
	
	@Override
	public MonthEntity queryObject(Integer mId){
		return monthDao.queryObject(mId);
	}

	@Override
	public List<MonthEntity>queryByYear(Map<String,Object>map){return monthDao.queryByYear(map);}
	
	@Override
	public List<MonthEntity> queryList(Map<String, Object> map){
		return monthDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return monthDao.queryTotal(map);
	}
	
	@Override
	public void save(MonthEntity month){
		monthDao.save(month);
	}

	@Override
	public void saveBatch(List<MonthEntity> monthList){
		monthDao.saveBatch(monthList);
	}

	@Override
	public void update(MonthEntity month){
		monthDao.update(month);
	}
	
	@Override
	public void delete(Integer mId){
		monthDao.delete(mId);
	}
	
	@Override
	public void deleteBatch(Integer[] mIds){
		monthDao.deleteBatch(mIds);
	}
	
}
