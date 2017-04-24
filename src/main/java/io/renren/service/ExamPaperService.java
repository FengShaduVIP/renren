package io.renren.service;

import io.renren.entity.ExamPaperEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-02-20 21:26:57
 */
public interface ExamPaperService {
	
	ExamPaperEntity queryObject(Integer id);
	
	List<ExamPaperEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ExamPaperEntity examPaper);
	
	void update(ExamPaperEntity examPaper);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}