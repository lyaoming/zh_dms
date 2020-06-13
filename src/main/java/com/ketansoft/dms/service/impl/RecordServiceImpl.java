package com.ketansoft.dms.service.impl;

import com.ketansoft.dms.entity.CostItemEntity;
import com.ketansoft.dms.entity.CprelationshipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.RecordDao;
import com.ketansoft.dms.entity.RecordEntity;
import com.ketansoft.dms.service.RecordService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-08 17:27:52
 */
@Service("recordService")
public class RecordServiceImpl implements RecordService {
	@Autowired
	private RecordDao recordDao;
	
	@Override
	public RecordEntity queryObject(Integer recordId){
		return recordDao.queryObject(recordId);
	}
	
	@Override
	public List<RecordEntity> findSame(Map<String, Object> map){
		return recordDao.findSame(map);
	}

	@Override
	public List<RecordEntity> queryList(Map<String, Object> map){
		return recordDao.queryList(map);
	}

	@Override
	public List<CostItemEntity>costList(Map<String, Object> map){
		return recordDao.costList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return recordDao.queryTotal(map);
	}
	
	@Override
	public void save(RecordEntity record){
		recordDao.save(record);
	}

	@Override
	public void saveBatch(List<RecordEntity> recordList){
		recordDao.saveBatch(recordList);
	}

	@Override
	public void update(RecordEntity record){
		recordDao.update(record);
	}
	
	@Override
	public void delete(Integer recordId){
		recordDao.delete(recordId);
	}
	
	@Override
	public void deleteBatch(Integer[] recordIds){
		recordDao.deleteBatch(recordIds);
	}
	
}
