package cn.e3mall.controller;
/**
 * 内容Controller
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月20日 下午7:37:13
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.common.pojo.DataGridResult;
import cn.common.pojo.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	// 查询内容列表
	@RequestMapping("/content/query/list")
	public @ResponseBody DataGridResult getContentListById(Long categoryId,int page,int rows) {
		DataGridResult result = contentService.pageQuery(categoryId,page,rows);
		return result;
	}
	
	//编辑内容
	@RequestMapping("/rest/content/edit")
	public @ResponseBody E3Result editContent(TbContent content) {
		E3Result result = contentService.updateContent(content);
		return result;
	}
	
	//新增内容
	@RequestMapping("/content/save")
	public @ResponseBody E3Result addContent(TbContent content) {
		E3Result result = contentService.addContent(content);
		return result;
	}
}
