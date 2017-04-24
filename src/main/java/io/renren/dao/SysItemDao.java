package io.renren.dao;

import io.renren.entity.SysItemEntity;

public interface SysItemDao extends BaseDao<SysItemEntity> {

	String findLastId();
	
}
