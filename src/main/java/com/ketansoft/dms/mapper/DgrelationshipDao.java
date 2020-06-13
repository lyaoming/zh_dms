package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.DgrelationshipEntity;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-22 16:38:25
 */
public interface DgrelationshipDao extends BaseDao<DgrelationshipEntity> {
    void saveBatch(List<DgrelationshipEntity> dgrelationshipList);

    List<DgrelationshipEntity> querySame(Map<String, Object> map);

    List<DgrelationshipEntity> peizhiList(Map<String, Object> map);
}
