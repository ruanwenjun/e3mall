package cn.e3mall.search.mapper;

import java.util.List;

import cn.e3mall.common.pojo.SearchItem;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月22日 下午3:37:52
 */
public interface ItemMapper {

	// 查询所有商品
	public List<SearchItem> selectAllItem();
}
