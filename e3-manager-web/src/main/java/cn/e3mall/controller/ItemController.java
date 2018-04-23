package cn.e3mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
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
	
	//根据ID查询商品
	@RequestMapping("/rest/item/param/item/query/{id}")
	public @ResponseBody E3Result item(@PathVariable Long id) {
		E3Result item = itemService.selectItemById(id);
		return item;
	}
	
	//获得商品列表返回给数据表格
	@RequestMapping("/item/list")
	public @ResponseBody DataGridResult getItemList(int page,int rows) {
		DataGridResult result = itemService.getDataGridResult(page, rows);
		return result;
	}
	
	//新增商品
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	public @ResponseBody E3Result addItem(TbItem item,String desc) {
		E3Result result = itemService.addNewItem(item, desc);
		return result;
	}
	
	// 查询商品详情
	@RequestMapping("/rest/item/query/item/desc/{id}")
	public @ResponseBody E3Result getItemDescById(@PathVariable Long id) {
		E3Result desc = itemService.getItemDescById(id);
		return desc;
	}
	
	//修改商品
	@RequestMapping("/rest/item/update")
	public @ResponseBody E3Result updateItem(TbItem item,String desc) {
		E3Result result = itemService.updateItem(item,desc);
		return result;
	}
	
	//删除商品
	@RequestMapping("/rest/item/delete")
	public @ResponseBody E3Result deleteItemById(String ids) {
		E3Result result = itemService.deleteItemById(ids);
		return result;
	}
	
	//商品下架
	@RequestMapping("/rest/item/instock")
	public @ResponseBody E3Result instockItem(String ids) {
		E3Result result = itemService.instockItem(ids);
		return result;
	}
	
	//商品上架
	@RequestMapping("/rest/item/reshelf")
	public @ResponseBody E3Result reshelfItem(String ids) {
		E3Result result = itemService.reshelfItem(ids);
		return result;
	}
}
