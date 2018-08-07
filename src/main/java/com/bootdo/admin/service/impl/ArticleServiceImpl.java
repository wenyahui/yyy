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

import com.bootdo.admin.dao.ArticleDao;
import com.bootdo.admin.dao.ColumnDao;
import com.bootdo.admin.entity.Article;
import com.bootdo.admin.entity.Column;
import com.bootdo.admin.service.ArticleService;
import com.bootdo.admin.service.ColumnService;

/** 
* @ClassName: ColumnServiceImpl 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午8:11:54 
*  
*/
@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleDao articleDao;

	@Override
	public List<Map<String, Object>> getArticleList(Map<String, Object> paramMap) {
		return articleDao.getArticleList(paramMap);
	}

	@Override
	public int queryArticleCount(Map<String, Object> paramMap) {
		return articleDao.queryArticleCount(paramMap);
	}

	@Override
	public void addArticle(Article article) {
		Date nowDate = new Date();
		article.setCreateTime(nowDate);
		article.setUpdateTime(nowDate);
		articleDao.insert(article);
	}

	@Override
	public void delArticle(Integer id) {
		articleDao.deleteByPrimaryKey(id);
	}

	@Override
	public void delArticles(String ids) {
		articleDao.delArticles(ids);
	}

	@Override
	public Article queryArticleById(Integer id) {
		return articleDao.selectByPrimaryKey(id);
	}

	@Override
	public void updateArticle(Article article) {
		articleDao.updateByPrimaryKeySelective(article);
	}
	
}
