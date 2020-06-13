package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.UseAdminDao;
import com.ketansoft.dms.entity.UseAdminEntity;
import com.ketansoft.dms.service.UseAdminService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-05 09:48:57
 */
@Service("useAdminService")
public class UseAdminServiceImpl implements UseAdminService {
	@Autowired
	private UseAdminDao useAdminDao;
	
	@Override
	public UseAdminEntity queryObject(Integer uId){
		return useAdminDao.queryObject(uId);
	}
	
	@Override
	public List<UseAdminEntity> queryList(Map<String, Object> map){
		return useAdminDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return useAdminDao.queryTotal(map);
	}
	
	@Override
	public void save(UseAdminEntity useAdmin){
		useAdminDao.save(useAdmin);
	}

	@Override
	public void saveBatch(List<UseAdminEntity> useAdminList){
		useAdminDao.saveBatch(useAdminList);
	}

	@Override
	public void update(UseAdminEntity useAdmin){
		useAdminDao.update(useAdmin);
	}
	
	@Override
	public void delete(Integer uId){
		useAdminDao.delete(uId);
	}
	
	@Override
	public void deleteBatch(Integer[] uIds){
		useAdminDao.deleteBatch(uIds);
	}
	
}
