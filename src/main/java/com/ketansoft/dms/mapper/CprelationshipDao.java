package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.CprelationshipEntity;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-28 09:04:22
 */
public interface CprelationshipDao extends BaseDao<CprelationshipEntity> {
    void saveBatch(List<CprelationshipEntity> cprelationshipList);

    List<CprelationshipEntity>queryRecordList(Map<String,Object>map);

    List<CprelationshipEntity>edqueryList(Map<String,Object>map);

    void leave(Map<String,Object>map);

    void leaveSelect(Map<String,Object>map);
}
