package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.MonthEntity;

import java.util.List;
import java.util.Map;
/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-08 13:36:25
 */
public interface MonthDao extends BaseDao<MonthEntity> {
    void saveBatch(List<MonthEntity> monthList);
    List<MonthEntity>queryByYear(Map<String,Object>map);
}
