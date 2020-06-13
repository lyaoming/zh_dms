package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.ShowDataDao;
import com.ketansoft.dms.entity.ShowDataEntity;
import com.ketansoft.dms.service.ShowDataService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-08-03 17:20:48
 */
@Service("showDataService")
public class ShowDataServiceImpl implements ShowDataService {
	@Autowired
	private ShowDataDao showDataDao;

	@Override
	public ShowDataEntity queryObject(Integer id){
		return showDataDao.queryObject(id);
	}

	@Override
	public List<ShowDataEntity> queryList(Map<String, Object> map){
		return showDataDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return showDataDao.queryTotal(map);
	}

	@Override
	public void save(ShowDataEntity showData){
		showDataDao.save(showData);
	}

	@Override
	public void saveBatch(List<ShowDataEntity> showDataList){
		showDataDao.saveBatch(showDataList);
	}

	@Override
	public void update(ShowDataEntity showData){
		showDataDao.update(showData);
	}

	@Override
	public void delete(Integer id){
		showDataDao.delete(id);
	}

	@Override
	public void deleteBatch(Integer[] ids){
		showDataDao.deleteBatch(ids);
	}

	@Override
	public void add(Map<String,Object>map){showDataDao.add(map);}

	@Override
	public void reduce(Map<String,Object>map){showDataDao.reduce(map);}

}
