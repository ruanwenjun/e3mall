package cn.e3mall.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.service.ItemService;

/**
 * 订单Controller
 * 
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月27日 下午4:45:01
 */
@Controller
public class CartController {

	@Autowired
	private ItemService itemService;
	@Value("${CART_COOKIE_MAXTIME}")
	private int CART_COOKIE_MAXTIME;
	@Autowired
	private CartService cartService;
	
	/**
	 * 添加商品到购物车，调换到添加成功页面
	 * 
	 * @param itemId
	 * @param num
	 * @return
	 */
	@RequestMapping("/cart/add/{itemId}.html")
	public String addItemToCart(@PathVariable Long itemId, int num, HttpServletRequest request,
			HttpServletResponse response) {
		// 判断用户是否登陆
		TbUser user = (TbUser) request.getAttribute("user");
		if( user != null) {
			//用户登陆的情况下，添加商品到redis
			cartService.addItem(user.getId(), itemId, num);
		}else {
			//用户没有登陆的情况下添加商品到cookie
			// 根据商品ID从数据库中查询商品信息
			TbItem item = itemService.selectItemById(itemId);
			// 设置商品图片
			String image = item.getImage();
			item.setImage(image.split(",")[0]);
			// 将商品数量写入
			item.setNum(num);
			// 获得cookie中的商品信息
			List<TbItem> cartList = getItemListOnCookie(request);
			// 判断cookie中是否存在当前添加商品的标识
			boolean flag = false;
			// 将商品信息写入cookie
			for (TbItem tbItem : cartList) {
				if (tbItem.getId().longValue() == itemId) {
					tbItem.setNum(tbItem.getNum() + num);
					flag = true;
					break;
				}
			}
			if (!flag) {
				// 新增商品到cookie
				cartList.add(item);
			}
			
			// 将商品列表写入cookie
			String itemCookie = JsonUtils.objectToJson(cartList);
			CookieUtils.setCookie(request, response, "cart", itemCookie, CART_COOKIE_MAXTIME, true);
		}
		// 跳转到添加成功列表
		return "cartSuccess";
	}

	/**
	 * 删除购物车中的商品，重定向到购物车列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId,HttpServletRequest request
			,HttpServletResponse response) {
		//判断用户是否登陆
		TbUser user = (TbUser) request.getAttribute("user");
		if(user != null ) {
			//用户登陆的情况下,删除redis中的商品
			cartService.deleteItem(user.getId(),itemId);
		}else {
			//用户未登陆的情况下，删除cookie中的商品
			// 获取cookie中的商品
			List<TbItem> cartList = getItemListOnCookie(request);
			for (TbItem tbItem : cartList) {
				if(tbItem.getId().longValue() == itemId) {
					cartList.remove(tbItem);
					break;
				}
			}
			String cartCookie = JsonUtils.objectToJson(cartList);
			// 更新cookie
			CookieUtils.setCookie(request, response, "cart",cartCookie , CART_COOKIE_MAXTIME, true);
		}
		// 重定向到购物车列表
		return "redirect:/cart/cart.html";
	}

	
	/**
	 * 查询购物车中商品列表，跳转到购物车列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/cart/cart")
	public String pageCart(HttpServletRequest request, HttpServletResponse response) {
		// 从cookie中得到商品
		List<TbItem> cartList = getItemListOnCookie(request);
		TbUser user = (TbUser) request.getAttribute("user");
		if(user != null ) {
			//当前有用户登陆
			// 将cookie中的商品与服务器端的商品合并
			cartService.mergeCart(user.getId(),cartList);
			// 从redis中查询用户的购物车
			cartList = cartService.selectCartByUserId(user.getId());
			//清除cookie中的购物车列表
			CookieUtils.deleteCookie(request, response, "cart");
		}
		request.setAttribute("cartList", cartList);
		// 重定向到购物车列表页面
		return "cart";
	}
	
	/**
	 * 修改购物车中的商品
	 * @param itemId
	 * @param num
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	public @ResponseBody String updateCart(@PathVariable Long itemId, @PathVariable int num
			, HttpServletRequest request,HttpServletResponse response) {
		//判断当前是否有用户登陆
		TbUser user = (TbUser) request.getAttribute("user");
		if(user != null) {
			//当前有用户登陆，修改redis中的商品数量
			cartService.updateItem(user.getId(),itemId,num);
		}else {
			// 获取cookie中的商品列表
			List<TbItem> cartList = getItemListOnCookie(request);
			// 根据ID修改商品
			for (TbItem tbItem : cartList) {
				if (tbItem.getId().longValue() == itemId) {
					if (num > 0) {
						tbItem.setNum(num);
						break;
					}
					// 移除购物车中的商品
					cartList.remove(tbItem);
					break;
				}
			}
			// 更新cookie
			String cartCookie = JsonUtils.objectToJson(cartList);
			CookieUtils.setCookie(request, response, "cart", cartCookie, CART_COOKIE_MAXTIME, true);
		}
		return "ok";
	}
	
	/**
	 * 私有方法
	 * 从Cookie中取得购物车商品列表
	 * 
	 * @return
	 */
	private List<TbItem> getItemListOnCookie(HttpServletRequest request) {
		// 获得cookie中的商品数据
		String cookieValue = CookieUtils.getCookieValue(request, "cart", "utf-8");
		// 不存在购物车cookie
		if (StringUtils.isBlank(cookieValue)) {
			return new ArrayList<TbItem>();
		}
		// 将购物车cookie转为list
		List<TbItem> cartList = JsonUtils.jsonToList(cookieValue, TbItem.class);
		return cartList;
		
	}
}
