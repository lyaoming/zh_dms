package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.DormDetailedEntity;
import com.ketansoft.dms.entity.DormtableEntity;

import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public interface DormDetailedDao extends BaseDao<DormDetailedEntity> {

    List<DormDetailedEntity> excelList();

    List<DormDetailedEntity>selectList(Map<String, Object> map);

    List<DormDetailedEntity>queryTimeList(Map<String, Object> map);
}
