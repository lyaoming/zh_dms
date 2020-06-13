package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.DepartmentEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-24 19:07:50
 */
public interface DepartmentDao extends BaseDao<DepartmentEntity> {

    void saveBatch(List<DepartmentEntity> departmentList);

    List<DepartmentEntity> querySameName(Map<String, Object> map);
}
