package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.ClassionDao;
import com.ketansoft.dms.entity.ClassionEntity;
import com.ketansoft.dms.service.ClassionService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-20 09:25:50
 */
@Service("classionService")
public class ClassionServiceImpl implements ClassionService {
	@Autowired
	private ClassionDao classionDao;
	
	@Override
	public ClassionEntity queryObject(Integer id){
		return classionDao.queryObject(id);
	}
	
	@Override
	public List<ClassionEntity> queryList(Map<String, Object> map){
		return classionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classionDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassionEntity classion){
		classionDao.save(classion);
	}

	@Override
	public void saveBatch(List<ClassionEntity> classionList){
		classionDao.saveBatch(classionList);
	}

	@Override
	public void update(ClassionEntity classion){
		classionDao.update(classion);
	}
	
	@Override
	public void delete(Integer id){
		classionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		classionDao.deleteBatch(ids);
	}
	
}
