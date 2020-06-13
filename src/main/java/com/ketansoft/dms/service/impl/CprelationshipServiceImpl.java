package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.CprelationshipDao;
import com.ketansoft.dms.entity.CprelationshipEntity;
import com.ketansoft.dms.service.CprelationshipService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-07-28 09:04:22
 */
@Service("cprelationshipService")
public class CprelationshipServiceImpl implements CprelationshipService {
	@Autowired
	private CprelationshipDao cprelationshipDao;
	
	@Override
	public CprelationshipEntity queryObject(Integer pId){
		return cprelationshipDao.queryObject(pId);
	}
	
	@Override
	public List<CprelationshipEntity> edqueryList(Map<String, Object> map){
		return cprelationshipDao.edqueryList(map);
	}

	@Override
	public List<CprelationshipEntity> queryList(Map<String, Object> map){
		return cprelationshipDao.queryList(map);
	}

	@Override
	public List<CprelationshipEntity> queryRecordList(Map<String, Object> map){
		return cprelationshipDao.queryRecordList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return cprelationshipDao.queryTotal(map);
	}
	
	@Override
	public void save(CprelationshipEntity cprelationship){
		cprelationshipDao.save(cprelationship);
	}

	@Override
	public void saveBatch(List<CprelationshipEntity> cprelationshipList){
		cprelationshipDao.saveBatch(cprelationshipList);
	}

	@Override
	public void update(CprelationshipEntity cprelationship){
		cprelationshipDao.update(cprelationship);
	}

	@Override
	public void leave(Map<String,Object>map){
		cprelationshipDao.leave(map);
	}

	@Override
	public void leaveSelect(Map<String,Object>map){
		cprelationshipDao.leaveSelect(map);
	}
	
	@Override
	public void delete(Integer pId){
		cprelationshipDao.delete(pId);
	}
	
	@Override
	public void deleteBatch(Integer[] pIds){
		cprelationshipDao.deleteBatch(pIds);
	}

}
