package io.renren.service;

import io.renren.entity.OutMoneySysEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author FengShadu
 * @email twpvip@gmail.com
 * @date 2017-04-25 21:13:12
 */
public interface OutMoneySysService {
	
	OutMoneySysEntity queryObject(Integer id);
	
	List<OutMoneySysEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OutMoneySysEntity outMoneySys);
	
	void update(OutMoneySysEntity outMoneySys);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
