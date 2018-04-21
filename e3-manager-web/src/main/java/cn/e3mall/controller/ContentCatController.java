package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.common.pojo.E3Result;
import cn.common.pojo.TreeNodeResult;
import cn.e3mall.content.service.ContentCatService;


/**
 * 首页内容分类Controller
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月20日 下午12:44:15
*/


@Controller
public class ContentCatController {
	
	@Autowired
	private ContentCatService contentCatService; 
	
	//获得首页内容分类列表
	@RequestMapping("/content/category/list")
	public @ResponseBody List<TreeNodeResult> 
		getContentCategoryList(@RequestParam(value="id",defaultValue="0")Long parentId) {
		
		List<TreeNodeResult> result = contentCatService.getContentCategoryList(parentId);
			return result;
		
	}
	
	//增加内容分类节点
	@RequestMapping("/content/category/create")
	public @ResponseBody E3Result addContentCategory(Long parentId,String name) {
		E3Result result = contentCatService.addContentCategory(parentId, name);
		return result;
		
	}
	
	//删除内容分类节点
	@RequestMapping("/content/category/delete/")
	public @ResponseBody String deleteContentCategory(Long id) {
		contentCatService.deleteContentCategory(id);
		return "ok";
	}
	
	//修改节点的内容
	@RequestMapping("/content/category/update")
	public @ResponseBody String updateContentCategory(Long id , String name) {
		contentCatService.updateContentCategory(id,name);
		return "ok";
	}
}
