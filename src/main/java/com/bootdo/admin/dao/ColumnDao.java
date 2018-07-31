package com.bootdo.admin.dao;

import java.util.List;
import java.util.Map;

import com.bootdo.admin.entity.Column;

public interface ColumnDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Column record);

    int insertSelective(Column record);

    Column selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Column record);

    int updateByPrimaryKey(Column record);
    
    List<Map<String, Object>> getColumnList(Map<String, Object> paramMap);
    
    void delColumn(Integer id);
    
    void delColumns(String ids);
	int checkColumnName(Map<String, Object> paramMap);
}