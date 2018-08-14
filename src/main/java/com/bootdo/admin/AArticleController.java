/**   
* @Title: AArticleController.java 
* @Package com.bootdo.admin 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午6:11:04 
* @version V1.0   
*/
package com.bootdo.admin;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.admin.entity.Article;
import com.bootdo.admin.service.ArticleService;
import com.bootdo.admin.service.ColumnService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/** 
* @ClassName: AArticleController 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午6:11:04 
*  
*/

@Controller
@RequestMapping("/MS/article")
public class AArticleController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ColumnService columnService;
	@Autowired
	private ArticleService articleService;
	/**
	 * 文章栏目列表
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<Map<String, Object>> columnList = columnService.getColumnList(null);
		model.addAttribute("columnList", columnList);
		return "admin/article/list";
	}
	
	@RequestMapping("/listData")
	@ResponseBody
	public PageUtils listData(Model model,@RequestParam Map<String, Object> paramMap) {
		Query query = new Query(paramMap);
		List<Map<String, Object>> list = articleService.getArticleList(query);
		int count = articleService.queryArticleCount(paramMap);
		PageUtils page = new PageUtils(list, count);
		return page;
	}

	@RequestMapping("/edit_{id}")
	public String toEdit(Model model,@PathVariable Integer id) {
		List<Map<String, Object>> columnList = columnService.getColumnList(null);
		model.addAttribute("columnList", columnList);
		if(id!=null) {
			Article article = articleService.queryArticleById(id);
			model.addAttribute("article", article);
		}
		return "admin/article/edit";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public R save(@ModelAttribute Article article) {
		if(article!=null) {
			if(article.getId()!=null) {
				articleService.updateArticle(article);
			}else {
				articleService.addArticle(article);
			}
			return R.ok();
		}else {
			return R.error();
		}
		
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public R del(@RequestParam Integer id) {
		articleService.delArticle(id);
		return R.ok();
	}
}
