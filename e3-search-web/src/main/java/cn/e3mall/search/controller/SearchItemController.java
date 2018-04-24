package cn.e3mall.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.service.SearchItemService;


/**
 * 前台页面查询商品的Controller
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月22日 下午9:11:00
*/
@Controller
public class SearchItemController {
	
	@Autowired
	private SearchItemService searchItemService;
	@Value("${SEARCH_PAGE_SIZE}")
	private int SEARCH_PAGE_SIZE;
	
	@RequestMapping("search")
	public String searchItem(String keyword,@RequestParam(defaultValue="1")int page,Model model) throws Exception {
		//解决关键字乱码
		keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
		
		
		SearchResult result = searchItemService.searchItemsByTitle(keyword, SEARCH_PAGE_SIZE, page);
		//将结果添加到request域
		model.addAttribute("recourdCount", result.getRecourdCount());
		model.addAttribute("totalPages", result.getTotalPages());
		model.addAttribute("page", page);
		model.addAttribute("query", keyword);
		model.addAttribute("itemList", result.getItemList());
		
		return "search";
	}
}
