package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.DormRoomEntity;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-30 13:44:30
 */
public interface DormRoomDao extends BaseDao<DormRoomEntity> {

    DormRoomEntity queryObjectG(Integer rId);

    List<DormRoomEntity>updateList (Map<String,Object>map);

    void saveBatch(List<DormRoomEntity> dormRoomList);
}
