package com.bootdo.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.bootdo.admin.entity.Column;

@Transactional
public interface ColumnService {
	
	/**
	 * 获取栏目列表 带参数
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> getColumnList(Map<String, Object> paramMap);
	
	void addColumn(Column column);
	
	void delColumn(Integer id);
	void delColumns(String ids);
	
	Column queryColumnById(Integer id);
	
	void updateColumn(Column column);
}
