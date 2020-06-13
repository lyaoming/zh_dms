package com.ketansoft.dms.service;

import com.ketansoft.dms.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 代码生成器
 * @date 2019-07-18 19:58:44
 */
public interface GoodsService {
	
	GoodsEntity queryObject(Integer gId);
	
	List<GoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsEntity goods);

	void saveBatch(List<GoodsEntity> goodsList);

	void update(GoodsEntity goods);
	
	void delete(Integer gId);
	
	void deleteBatch(Integer[] gIds);
}
