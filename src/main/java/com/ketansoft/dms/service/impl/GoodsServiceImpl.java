package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.GoodsDao;
import com.ketansoft.dms.entity.GoodsEntity;
import com.ketansoft.dms.service.GoodsService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-07-18 19:58:44
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;
	
	@Override
	public GoodsEntity queryObject(Integer gId){
		return goodsDao.queryObject(gId);
	}
	
	@Override
	public List<GoodsEntity> queryList(Map<String, Object> map){
		return goodsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsEntity goods){
		goodsDao.save(goods);
	}

	@Override
	public void saveBatch(List<GoodsEntity> goodsList){
		goodsDao.saveBatch(goodsList);
	}

	@Override
	public void update(GoodsEntity goods){
		goodsDao.update(goods);
	}
	
	@Override
	public void delete(Integer gId){
		goodsDao.delete(gId);
	}
	
	@Override
	public void deleteBatch(Integer[] gIds){
		goodsDao.deleteBatch(gIds);
	}
	
}
