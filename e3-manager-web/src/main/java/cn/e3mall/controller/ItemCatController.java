package cn.e3mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.e3mall.common.pojo.TreeNodeResult;
import cn.e3mall.service.ItemCatService;

/**
 * 商品列表controller
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月18日 上午10:30:40
*/
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService service;
	
	@RequestMapping("/item/cat/list")
	public @ResponseBody List<TreeNodeResult> getItemCatList(
			@RequestParam(value="id",defaultValue="0") long parentId) {
		
		List<TreeNodeResult> itemCatList = service.getItemCatList(parentId);
		return itemCatList;
	}
	
	
}
