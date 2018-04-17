package cn.e3mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.e3mall.dao.TbItemMapper;
import cn.e3mall.pojo.TbItem;
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
}
