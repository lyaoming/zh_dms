package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.ShowDataEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-03 17:20:48
 */
public interface ShowDataDao extends BaseDao<ShowDataEntity> {

    void saveBatch(List<ShowDataEntity> showDataList);

   void add(Map<String,Object>map);

   void reduce(Map<String,Object>map);

}
