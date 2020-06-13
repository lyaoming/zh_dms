package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.DprelationshipEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-19 16:41:52
 */
public interface DprelationshipDao extends BaseDao<DprelationshipEntity> {
    int queryRecordTotal(Map<String,Object>map);

    void saveBatch(List<DprelationshipEntity> dprelationshipList);

    List<DprelationshipEntity> queryObjectStatus(Map<String, Object> map);

    List<DprelationshipEntity> querySameList(Map<String, Object> map);

    List<DprelationshipEntity> queryRecordList(Map<String, Object> map);

    List<DprelationshipEntity> queryRetreateList(Map<String, Object> map);

    List<DprelationshipEntity> queryTimeList(Map<String, Object> map);

    List<DprelationshipEntity> findPersonnel(Map<String, Object> map);

    void leaveSelect(Map<String, Object> map);

    Boolean leave(Map<String, Object> map);

    List<DprelationshipEntity>exportSelect(Integer[] pdIds);
}
