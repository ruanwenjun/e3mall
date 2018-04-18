package cn.e3mall.service;

import cn.common.pojo.DataGridResult;
import cn.e3mall.pojo.TbItem;

/**
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月16日 下午9:17:51
*/
public interface ItemService {
	public TbItem selectItemById(long id) ;
	public DataGridResult getDataGridResult(int page, int rows);
}
