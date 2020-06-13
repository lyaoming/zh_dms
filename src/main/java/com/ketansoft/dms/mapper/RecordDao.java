package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.CostItemEntity;
import com.ketansoft.dms.entity.CprelationshipEntity;
import com.ketansoft.dms.entity.RecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-08 17:27:52
 */
public interface RecordDao extends BaseDao<RecordEntity> {
   void saveBatch(List<RecordEntity> recordList);

   List<CostItemEntity>costList(Map<String,Object>map);

   List<RecordEntity>findSame(Map<String,Object>map);
}
