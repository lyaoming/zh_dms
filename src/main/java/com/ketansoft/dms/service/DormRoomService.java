package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.DormRoomEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-30 13:44:30
 */
public interface DormRoomService {
	
	DormRoomEntity queryObject(Integer pId);

	DormRoomEntity queryObjectG(Integer rId);
	
	List<DormRoomEntity> queryList(Map<String, Object> map);

	List<DormRoomEntity>updateList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DormRoomEntity dormRoom);

	void saveBatch(List<DormRoomEntity> dormRoomList);

	void update(DormRoomEntity dormRoom);
	
	void delete(Integer nodeId);
	
	void deleteBatch(Integer[] nodeIds);
}
