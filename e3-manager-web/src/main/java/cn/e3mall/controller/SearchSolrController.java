package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.search.service.SearchService;

/**
 * 
 * 添加索引Controller
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月22日 下午4:08:38
 */
@Controller
public class SearchSolrController {

	@Autowired
	private SearchService searchService;

	// 将所有商品添加到索引库
	@RequestMapping("/index/item/import")
	public @ResponseBody E3Result importSearchSolr() {
		E3Result result = searchService.addAllToSolr();
		return result;
	}

}
