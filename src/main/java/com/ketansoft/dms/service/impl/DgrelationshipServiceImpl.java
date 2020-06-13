package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.DgrelationshipDao;
import com.ketansoft.dms.entity.DgrelationshipEntity;
import com.ketansoft.dms.service.DgrelationshipService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-07-22 16:38:25
 */
@Service("dgrelationshipService")
public class DgrelationshipServiceImpl implements DgrelationshipService {
	@Autowired
	private DgrelationshipDao dgrelationshipDao;
	
	@Override
	public DgrelationshipEntity queryObject(Integer gdId){
		return dgrelationshipDao.queryObject(gdId);
	}
	
	@Override
	public List<DgrelationshipEntity> queryList(Map<String, Object> map){
		return dgrelationshipDao.queryList(map);
	}


	@Override
	public List<DgrelationshipEntity> querySame(Map<String, Object> map){
		return dgrelationshipDao.querySame(map);
	}


	@Override
	public List<DgrelationshipEntity>peizhiList(Map<String, Object> map){
		return dgrelationshipDao.peizhiList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return dgrelationshipDao.queryTotal(map);
	}
	
	@Override
	public void save(DgrelationshipEntity dgrelationship){
		dgrelationshipDao.save(dgrelationship);
	}

	@Override
	public void saveBatch(List<DgrelationshipEntity> dgrelationshipList){
		dgrelationshipDao.saveBatch(dgrelationshipList);
	}

	@Override
	public void update(DgrelationshipEntity dgrelationship){
		dgrelationshipDao.update(dgrelationship);
	}
	
	@Override
	public void delete(Integer gdId){
		dgrelationshipDao.delete(gdId);
	}
	
	@Override
	public void deleteBatch(Integer[] gdIds){
		dgrelationshipDao.deleteBatch(gdIds);
	}
	
}
