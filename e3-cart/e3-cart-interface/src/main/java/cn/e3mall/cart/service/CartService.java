package cn.e3mall.cart.service;

import java.util.List;
import cn.e3mall.pojo.TbItem;

/**
 * 购物车服务接口
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月28日 上午10:02:07
*/
public interface CartService {
	/**
	 * 根据用户 id 和商品列表， 合并redis中的购物车列表
	 * @param id
	 * @param cartList
	 * @return
	 */
	void mergeCart(Long userId, List<TbItem> cartList);
	/**
	 * 将指定数目商品添加到redis中的指定用户
	 * @param userId
	 * @param itemId
	 * @param num
	 */
	void addItem(Long userId,Long itemId,int num);
	/**
	 * 从redis中查询用户的购物车商品
	 * @param id
	 * @return 
	 */
	List<TbItem> selectCartByUserId(Long userId);
	/**
	 * 在redis中删除用户的指定商品
	 * @param id
	 * @param itemId
	 */
	void deleteItem(Long userId, Long itemId);
	/**
	 * 在redis中更新商品的数量
	 * @param id
	 * @param itemId
	 * @param num
	 */
	void updateItem(Long userId, Long itemId, int num);
}
