/**   
* @Title: MenuServiceImpl.java 
* @Package com.bootdo.admin.service.impl 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午8:11:54 
* @version V1.0   
*/
package com.bootdo.admin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.admin.dao.ColumnDao;
import com.bootdo.admin.entity.Column;
import com.bootdo.admin.service.ColumnService;

/** 
* @ClassName: ColumnServiceImpl 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午8:11:54 
*  
*/
@Service
public class ColumnServiceImpl implements ColumnService{
	
	@Autowired
	private ColumnDao columnDao;

	@Override
	public List<Map<String, Object>> getColumnList(Map<String, Object> paramMap) {
		return columnDao.getColumnList(paramMap);
	}

	@Override
	public void addColumn(Column column) {
		Date nowDate = new Date();
		column.setCreateTime(nowDate);
		column.setUpdateTime(nowDate);
		columnDao.insert(column);
	}

	@Override
	public void delColumn(Integer id) {
		columnDao.delColumn(id);
	}

	@Override
	public void delColumns(String ids) {
		columnDao.delColumns(ids);
	}

	@Override
	public Column queryColumnById(Integer id) {
		return columnDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateColumn(Column column) {
		columnDao.updateByPrimaryKey(column);
	}

}
