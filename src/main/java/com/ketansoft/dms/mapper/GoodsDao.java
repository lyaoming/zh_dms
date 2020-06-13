package com.ketansoft.dms.mapper;

import com.framework.dao.BaseDao;
import com.ketansoft.dms.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-18 19:58:44
 */
public interface GoodsDao extends BaseDao<GoodsEntity> {
    void saveBatch(List<GoodsEntity> goodsList);

}
