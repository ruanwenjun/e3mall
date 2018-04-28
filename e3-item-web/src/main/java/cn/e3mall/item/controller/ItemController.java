package cn.e3mall.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.item.pojo.Item;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;

/**
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月24日 下午2:50:55
 */
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	
	@RequestMapping("item/{itemId}")
	public String itemDetail(@PathVariable Long itemId,Model model) {
		//根据ID查询商品
		TbItem tbItem = itemService.selectItemById(itemId);
		TbItemDesc itemDesc = itemService.getItemDescById(itemId);
		//将商品包装
		Item item = new Item(tbItem);
		//添加到request域
		model.addAttribute("item",item);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
}
