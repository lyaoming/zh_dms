package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.DormRoomDao;
import com.ketansoft.dms.entity.DormRoomEntity;
import com.ketansoft.dms.service.DormRoomService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-07-30 13:44:30
 */
@Service("dormRoomService")
public class DormRoomServiceImpl implements DormRoomService {
	@Autowired
	private DormRoomDao dormRoomDao;
	
	@Override
	public DormRoomEntity queryObject(Integer pId){
		return dormRoomDao.queryObject(pId);
	}

	@Override
	public DormRoomEntity queryObjectG(Integer rId){
		return dormRoomDao.queryObjectG(rId);
	}


	@Override
	public List<DormRoomEntity> queryList(Map<String, Object> map){
		return dormRoomDao.queryList(map);
	}

	@Override
	public List<DormRoomEntity> updateList(Map<String, Object> map){
		return dormRoomDao.updateList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dormRoomDao.queryTotal(map);
	}
	
	@Override
	public void save(DormRoomEntity dormRoom){
		dormRoomDao.save(dormRoom);
	}

	@Override
	public void saveBatch(List<DormRoomEntity> dormRoomList){
		dormRoomDao.saveBatch(dormRoomList);
	}

	@Override
	public void update(DormRoomEntity dormRoom){
		dormRoomDao.update(dormRoom);
	}
	
	@Override
	public void delete(Integer nodeId){
		dormRoomDao.delete(nodeId);
	}
	
	@Override
	public void deleteBatch(Integer[] nodeIds){
		dormRoomDao.deleteBatch(nodeIds);
	}
	
}
