package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.DepartmentDao;
import com.ketansoft.dms.entity.DepartmentEntity;
import com.ketansoft.dms.service.DepartmentService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-07-24 19:07:50
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public DepartmentEntity queryObject(Integer dpmId){
		return departmentDao.queryObject(dpmId);
	}
	
	@Override
	public List<DepartmentEntity> queryList(Map<String, Object> map){
		return departmentDao.queryList(map);
	}

	@Override
	public List<DepartmentEntity> querySameName(Map<String, Object> map){
		return departmentDao.querySameName(map);
	}



	@Override
	public int queryTotal(Map<String, Object> map){
		return departmentDao.queryTotal(map);
	}
	
	@Override
	public void save(DepartmentEntity department){
		departmentDao.save(department);
	}

	@Override
	public void saveBatch(List<DepartmentEntity> departmentList){
		departmentDao.saveBatch(departmentList);
	}

	@Override
	public void update(DepartmentEntity department){
		departmentDao.update(department);
	}
	
	@Override
	public void delete(Integer dpmId){
		departmentDao.delete(dpmId);
	}
	
	@Override
	public void deleteBatch(Integer[] dpmIds){
		departmentDao.deleteBatch(dpmIds);
	}
	
}
