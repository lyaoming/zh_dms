package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.ClassionEntity;

import java.util.List;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-20 09:25:50
 */
public interface ClassionDao extends BaseDao<ClassionEntity> {
    void saveBatch(List<ClassionEntity> classionList);
}
