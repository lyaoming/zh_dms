package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.CostEntity;
import com.ketansoft.dms.entity.ExpensetableEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-26 10:31:20
 */
public interface CostDao extends BaseDao<CostEntity> {
    void saveBatch(List<CostEntity> costList);

    List<ExpensetableEntity>queryRecordList(Map<String,Object>map);
}
