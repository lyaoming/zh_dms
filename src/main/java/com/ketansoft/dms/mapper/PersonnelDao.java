package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.PersonnelEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-17 10:08:23
 */
public interface PersonnelDao extends BaseDao<PersonnelEntity> {
    void saveBatch(List<PersonnelEntity> personnelList);

    List<PersonnelEntity> queryNowList(Map<String, Object> map);

    List<PersonnelEntity> querySame(Map<String, Object> map);


}
