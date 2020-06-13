package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.UseAdminEntity;

import java.util.List;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-08-05 09:48:57
 */
public interface UseAdminDao extends BaseDao<UseAdminEntity> {
    void saveBatch(List<UseAdminEntity> useAdminList);
}
