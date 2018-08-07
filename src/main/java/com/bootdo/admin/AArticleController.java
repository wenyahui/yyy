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

import com.bootdo.admin.entity.Column;
import com.bootdo.admin.service.ColumnService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;

/** 
* @ClassName: AArticleController 
* @Description: TODO
* @author wyh<18749563260@139.com>
* @date 2018年6月5日 下午6:11:04 
*  
*/

@Controller
@RequestMapping("/MS/column")
public class AArticleController extends BaseController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ColumnService columnService;
	/**
	 * 文章栏目列表
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model,@RequestParam Map<String, Object> paramMap) {
		List<Map<String, Object>> list = columnService.getColumnList(paramMap);
		model.addAttribute("list", list);
		return "admin/article/column/list";
	}

	@RequestMapping("/edit_{id}")
	public String toEdit(Model model,@PathVariable Integer id) {
		if(id!=null) {
			Column column = columnService.queryColumnById(id);
			model.addAttribute("column", column);
		}
		return "admin/article/column/edit";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public R save(@ModelAttribute Column column) {
		if(column!=null) {
			if(column.getId()!=null) {
				columnService.updateColumn(column);
			}else {
				columnService.addColumn(column);
			}
			return R.ok();
		}else {
			return R.error();
		}
		
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public R del(@RequestParam Integer id) {
		columnService.delColumn(id);
		return R.ok();
	}
	@RequestMapping("/checkColumnName")
	@ResponseBody
	public String checkColumnName(@RequestParam Map<String, Object> paramMap) {
		return columnService.checkColumnName(paramMap);
	}
}
