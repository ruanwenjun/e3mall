package cn.e3mall.protal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;

/**
 * 
 * 首页Controller
 * 
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月20日 上午11:54:10
 */
@Controller
public class IndexController {

	@Value(value="${AD1_CATEGORY_ID}")
	private Long AD1_CATEGORY_ID;

	@Autowired
	private ContentService contentService;

	// 跳转到首页
	@RequestMapping("/index.html")
	public String index(Model model) {
		// 查询广告轮播图
		List<TbContent> ad1List = contentService.selectContentListByCategoryId(AD1_CATEGORY_ID);
		model.addAttribute("ad1List", ad1List);

		return "index";
	}
}
