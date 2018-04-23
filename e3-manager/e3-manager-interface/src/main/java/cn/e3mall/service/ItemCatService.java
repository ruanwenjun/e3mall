package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.TreeNodeResult;


/**
 * 商品目录服务
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月18日 上午10:34:38
*/
public interface ItemCatService {
	
	public List<TreeNodeResult> getItemCatList(long parentId);
}
