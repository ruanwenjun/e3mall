package cn.e3mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.common.pojo.DataGridResult;
import cn.e3mall.dao.TbItemMapper;
import cn.e3mall.pojo.TbItem;
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
	
	//根据ID查询商品
	public TbItem selectItemById(long id) {
		TbItem item = itemMapper.selectByPrimaryKey(id);
		return item;
	}

	@Override
	public DataGridResult getDataGridResult(int page, int rows) {
		//分页插件
		PageHelper.startPage(page, rows);
		
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		PageInfo<TbItem> info = new PageInfo<>(list);
		
		//获得总数
		List<TbItem> itemList = info.getList();
		int size = (int) info.getTotal();
		//封装结果集
		DataGridResult result = new DataGridResult();
		result.setRows(itemList);
		result.setTotal(size);
		
		return result;
	}
}
