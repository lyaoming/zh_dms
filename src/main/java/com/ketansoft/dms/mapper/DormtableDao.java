package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.DormtableEntity;
import com.ketansoft.dms.entity.ExpensetableEntity;

import java.util.List;
import java.util.Map;

/**
 * Date:2019-7-25
 * Author:MaKaicheng
 * Desc：
 */
public interface DormtableDao extends BaseDao<DormtableEntity> {

    List<DormtableEntity> excelList();

    List<DormtableEntity>selectList(Map<String, Object> map);

    List<DormtableEntity>queryTimeList(Map<String, Object> map);

    List<DormtableEntity>ControlList1(Map<String, Object> map);

    List<DormtableEntity>ControlList2(Map<String, Object> map);

    int ControlTotal1(Map<String, Object> map);

    int ControlTotal2(Map<String, Object> map);
}
