package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.DprelationshipDao;
import com.ketansoft.dms.entity.DprelationshipEntity;
import com.ketansoft.dms.service.DprelationshipService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-07-19 16:41:52
 */
@Service("dprelationshipService")
public class DprelationshipServiceImpl implements DprelationshipService {
	@Autowired
	private DprelationshipDao dprelationshipDao;
	
	@Override
	public DprelationshipEntity queryObject(Integer pdId){
		return dprelationshipDao.queryObject(pdId);
	}
	
	@Override
	public List<DprelationshipEntity> queryList(Map<String, Object> map){
		return dprelationshipDao.queryList(map);
	}

	@Override
	public List<DprelationshipEntity> queryRetreateList(Map<String, Object> map){
		return dprelationshipDao.queryRetreateList(map);
	}

	@Override
	public List<DprelationshipEntity> queryRecordList(Map<String, Object> map){
		return dprelationshipDao.queryRecordList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dprelationshipDao.queryTotal(map);
	}

	@Override
	public int queryRecordTotal(Map<String, Object> map){
		return dprelationshipDao.queryRecordTotal(map);
	}


	@Override
	public void save(DprelationshipEntity dprelationship){
		dprelationshipDao.save(dprelationship);
	}

	@Override
	public void saveBatch(List<DprelationshipEntity> dprelationshipList){
		dprelationshipDao.saveBatch(dprelationshipList);
	}

	@Override
	public void update(DprelationshipEntity dprelationship){
		dprelationshipDao.update(dprelationship);
	}

	@Override
	public void leave(Map<String, Object> map){dprelationshipDao.leave(map);}

	@Override
	public List<DprelationshipEntity> queryObjectStatus(Map<String, Object> map){
		return dprelationshipDao.queryObjectStatus(map);
	}

	@Override
	public List<DprelationshipEntity> querySameList(Map<String, Object> map){
		return dprelationshipDao.querySameList(map);
	}

	@Override
	public List<DprelationshipEntity> findPersonnel(Map<String, Object> map) {
		return dprelationshipDao.findPersonnel(map);
	}

	@Override
	public void leaveSelect(Map<String, Object> map){dprelationshipDao.leaveSelect(map);}
	
	@Override
	public void delete(Integer pdId){
		dprelationshipDao.delete(pdId);
	}
	
	@Override
	public void deleteBatch(Integer[] pdIds){
		dprelationshipDao.deleteBatch(pdIds);
	}

	@Override
	public List<DprelationshipEntity>exportSelect(Integer[] pdIds){
	return 	dprelationshipDao.exportSelect(pdIds);
	}
	
}
