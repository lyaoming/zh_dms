package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;

import com.ketansoft.dms.entity.ExpensetableEntity;

import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public interface ExpensetableDao extends BaseDao<ExpensetableEntity> {

    List<ExpensetableEntity>excelList(Map<String, Object> map);

    List<ExpensetableEntity>selectList(Map<String, Object> map);

}
