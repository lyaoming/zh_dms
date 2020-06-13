package com.ketansoft.dms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ketansoft.dms.mapper.DormDao;
import com.ketansoft.dms.entity.DormEntity;
import com.ketansoft.dms.service.DormService;


/**
 * 
 *
 * @author 代码生成器
 * @date 2019-07-17 13:40:46
 */
@Service("dormService")
public class DormServiceImpl implements DormService {
	@Autowired
	private DormDao dormDao;
	
	@Override
	public DormEntity queryObject(Integer dId){
		return dormDao.queryObject(dId);
	}
	
	@Override
	public List<DormEntity> queryList(Map<String, Object> map){
		return dormDao.queryList(map);
	}

	@Override
	public List<DormEntity> queryUseAdmin(Map<String, Object> map){
		return dormDao.queryUseAdmin(map);
	}


	@Override
	public Integer queryObjectTotal(Integer dId){
		return dormDao.queryObjectTotal(dId);
	}


	@Override
	public int queryTotal(Map<String, Object> map){
		return dormDao.queryTotal(map);
	}

	@Override
	public int queryUseAdminTotal(Map<String, Object> map){
		return dormDao.queryUseAdminTotal(map);
	}
	
	@Override
	public void save(DormEntity dorm){
		dormDao.save(dorm);
	}

	@Override
	public void saveBatch(List<DormEntity> dormList){
		dormDao.saveBatch(dormList);
	}



	@Override
	public void add(Integer dId){dormDao.add(dId); }

	@Override
	public void reduce(Integer dId){dormDao.reduce(dId); }

	@Override
	public void update(DormEntity dorm){
		dormDao.update(dorm);
	}
	
	@Override
	public void delete(Integer dId){
		dormDao.delete(dId);
	}

	@Override
	public void deletep(Integer pId){dormDao.deletep(pId);}

	@Override
	public void deletec(Integer[] cIds){dormDao.deletec(cIds);}

	@Override
	public void deleteBatch(Integer[] dIds){
		dormDao.deleteBatch(dIds);
	}
	
}
