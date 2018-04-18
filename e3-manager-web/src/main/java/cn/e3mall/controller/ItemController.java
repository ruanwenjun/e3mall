package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.common.pojo.DataGridResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;

/**
 * 商品Controller
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月16日 下午9:23:42
*/
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/id")
	public @ResponseBody TbItem item(@PathVariable Long id) {
		TbItem item = itemService.selectItemById(id);
		return item;
	}
	
	@RequestMapping("/item/list")
	public @ResponseBody DataGridResult getItemList(int page,int rows) {
		DataGridResult result = itemService.getDataGridResult(page, rows);
		return result;
	}
}
