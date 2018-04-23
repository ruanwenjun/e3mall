package cn.e3mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.IDUtils;
import cn.e3mall.dao.TbItemDescMapper;
import cn.e3mall.dao.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.service.ItemService;

/**
 * 商品Service
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月16日 下午9:19:10
*/
@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	//根据ID查询商品
	public E3Result selectItemById(long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		E3Result result = new E3Result();
		result.setStatus(200);
		result.setData(item);
		return result;
	}
	
	//分页查询商品
	public DataGridResult getDataGridResult(int page, int rows) {
		//分页插件
		PageHelper.startPage(page, rows);
		
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> info = new PageInfo<>(list);
		
		//获得总数
		List<TbItem> itemList = info.getList();
		long size = info.getTotal();
		//封装结果集
		DataGridResult result = new DataGridResult();
		result.setRows(itemList);
		result.setTotal(size);
		
		return result;
	}
	
	//新增商品
	public E3Result addNewItem(TbItem item, String desc) {
		//为item封装剩余属性
		item.setId(IDUtils.genItemId());
		item.setStatus((byte) 1);
		item.setCreated(new Date());
		item.setUpdated(new Date());
		//保存Item
		itemMapper.insert(item);
		
		//保存商品详情
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		
		//返回结果 
		E3Result result = new E3Result();
		// 200 表示正常
		result.setStatus(200);
		return result;
	}
	
	// 根据ID查询商品详情
	public E3Result getItemDescById(Long id) {
		
		TbItemDesc desc = itemDescMapper.selectByPrimaryKey(id);
		E3Result result = new E3Result();
		result.setStatus(200);
		result.setData(desc);
		return result;
		
	}

	//更新商品还有商品详情
	public E3Result updateItem(TbItem item, String desc) {
		//更新商品
		item.setUpdated(new Date());
		int result1 = itemMapper.updateByPrimaryKeySelective(item);
		//更新商品详情
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		int result2 = itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		//封装返回结果集
		E3Result e3Result = new E3Result();
		if(result1 > 0 && result2 > 0 ) {
			e3Result.setStatus(200);
		}else {
			e3Result.setStatus(204);
			e3Result.setMsg("更新失败");
		}
		
		return e3Result;
	}
	
	//批量删除商品
	public E3Result deleteItemById(String ids) {
		String[] split = ids.split(",");
		for (String string : split) {
			Long id = new Long(string);
			itemMapper.deleteByPrimaryKey(id);
			itemDescMapper.deleteByPrimaryKey(id);
		}
		
		return E3Result.ok();
	}
	
	//批量下架商品
	public E3Result instockItem(String ids) {
		String[] split = ids.split(",");
		for (String string : split) {
			Long id = new Long(string);
			TbItem item = new TbItem();
			item.setId(id);
			item.setStatus((byte) 2);
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return E3Result.ok();
	}
	
	// 上架商品
	public E3Result reshelfItem(String ids) {
		String[] split = ids.split(",");
		for (String string : split) {
			Long id = new Long(string);
			TbItem item = new TbItem();
			item.setId(id);
			item.setStatus((byte) 1);
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return E3Result.ok();
	}
	
	
}
