package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.UseUnitEntity;

import java.util.List;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-05 15:26:38
 */
public interface UseUnitDao extends BaseDao<UseUnitEntity> {
    void saveBatch(List<UseUnitEntity> useUnitList);
}
