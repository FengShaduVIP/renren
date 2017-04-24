package io.renren.service;

import io.renren.entity.SysItemEntity;

import java.util.List;
import java.util.Map;


public interface SysItemService {
	
	SysItemEntity queryObject(Integer id);
	
	List<SysItemEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysItemEntity sysItem);
	
	void update(SysItemEntity sysItem);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void changeItems(Integer[] ids);
	
	String findLastId();
}
