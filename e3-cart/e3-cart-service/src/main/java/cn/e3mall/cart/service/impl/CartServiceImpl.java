package cn.e3mall.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.dao.TbItemMapper;
import cn.e3mall.pojo.TbItem;

/**
 * 购物车服务
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月28日 上午10:02:47
*/
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private TbItemMapper itemMapper;
	
	@Override
	public void mergeCart(Long userId, List<TbItem> cartList) {
		for (TbItem tbItem : cartList) {
			// 将当前商品的添加到redis中
			addItem(userId,tbItem.getId(),tbItem.getNum());
		}
		
	}

	@Override
	public void addItem(Long userId, Long itemId, int num) {
		// 根据指定用户的Id和商品Id查询商品
		String json = jedisClient.hget("USER_CART:"+userId, itemId+"");
		TbItem item = null;
		if(StringUtils.isBlank(json)) {
			// 原购物车中不存在该商品
			item = itemMapper.selectByPrimaryKey(itemId);
			item.setNum(num);
			json = JsonUtils.objectToJson(item);
		}else {
			//原购物车中存在该商品
			item = JsonUtils.jsonToPojo(json, TbItem.class);
			item.setNum(item.getNum() + num);
		}
		json = JsonUtils.objectToJson(item);
		//将商品写入redis
		jedisClient.hset("USER_CART:"+userId, itemId+"", json);
	}
	
	@Override
	public void deleteItem(Long userId, Long itemId) {
		jedisClient.hdel("USER_CART:"+userId, itemId+"");
	}
	
	@Override
	public List<TbItem> selectCartByUserId(Long userId) {
		//获得redis中的商品列表----json字符串
		List<String> jsonList = jedisClient.hvals("USER_CART:"+userId);
		List<TbItem> itemList = new ArrayList<TbItem>();
		//将json字符串转化为商品
		for (String json : jsonList) {
			TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
			itemList.add(item);
		}
		return itemList;
	}

	@Override
	public void updateItem(Long userId, Long itemId, int num) {
		//获取商品信息
		String json = jedisClient.hget("USER_CART:"+userId, itemId+"");
		TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
		item.setNum(num);
		//将更新后的商品写回redis
		jedisClient.hset("USER_CART:"+userId, itemId+"", JsonUtils.objectToJson(item));
	}

}
