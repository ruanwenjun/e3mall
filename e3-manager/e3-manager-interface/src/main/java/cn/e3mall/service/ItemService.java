package cn.e3mall.service;

import cn.e3mall.common.pojo.DataGridResult;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbItem;

/**
 * 商品service
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月16日 下午9:17:51
*/
public interface ItemService {
	

	
	public DataGridResult getDataGridResult(int page, int rows);
	
	/**
	 * 添加新商品
	 * @param item
	 * @param desc
	 * @return
	 */
	public E3Result addNewItem(TbItem item,String desc);
	/**
	 * 根据ID查询商品详情
	 * @param id
	 * @return
	 */
	public E3Result getItemDescById(Long id);
	/**
	 * 根据Id查询商品
	 * @param id
	 * @return
	 */
	public E3Result selectItemById(long id) ;
	
	/**
	 * 更新商品
	 * @param item
	 * @param desc
	 * @return
	 */
	public E3Result updateItem(TbItem item,String desc);
	
	/**
	 * 批量删除商品
	 * @param ids
	 * @return
	 */
	public E3Result deleteItemById(String ids);
	
	/**
	 * 商品下架
	 * @param ids
	 * @return
	 */
	public E3Result instockItem(String ids);
	
	/**
	 * 商品上架
	 * @param ids
	 * @return
	 */
	public E3Result reshelfItem(String ids);
}
