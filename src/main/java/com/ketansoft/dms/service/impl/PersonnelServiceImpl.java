package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.PersonnelDao;
import com.ketansoft.dms.entity.PersonnelEntity;
import com.ketansoft.dms.service.PersonnelService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-07-17 10:08:23
 */
@Service("personnelService")
public class PersonnelServiceImpl implements PersonnelService {
	@Autowired
	private PersonnelDao personnelDao;
	
	@Override
	public PersonnelEntity queryObject(Integer pId){
		return personnelDao.queryObject(pId);
	}
	
	@Override
	public List<PersonnelEntity> queryList(Map<String, Object> map){
		return personnelDao.queryList(map);
	}

	@Override
	public List<PersonnelEntity> queryNowList(Map<String, Object> map){
		return personnelDao.queryNowList(map);
	}

	@Override
	public List<PersonnelEntity> querySame(Map<String, Object> map){
		return personnelDao.querySame(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return personnelDao.queryTotal(map);
	}
	
	@Override
	public void save(PersonnelEntity personnel){
		personnelDao.save(personnel);
	}

	@Override
	public void saveBatch(List<PersonnelEntity> personnelList){
		personnelDao.saveBatch(personnelList);
	}

	@Override
	public void update(PersonnelEntity personnel){
		personnelDao.update(personnel);
	}
	
	@Override
	public void delete(Integer pId){
		personnelDao.delete(pId);
	}
	
	@Override
	public void deleteBatch(Integer[] pIds){
		personnelDao.deleteBatch(pIds);
	}
	
}
