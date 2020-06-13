package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.DormEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-17 13:40:46
 */
public interface DormDao extends BaseDao<DormEntity> {

    void saveBatch(List<DormEntity> dormList);

    List<DormEntity>queryUseAdmin(Map<String,Object>map);

    int queryUseAdminTotal(Map<String,Object>map);

    Integer queryObjectTotal(Integer dId);

    void add(Integer dId);

    void reduce(Integer BdId);

    void deletep(Integer pId);

    void deletec(Integer[] cIds);
}
