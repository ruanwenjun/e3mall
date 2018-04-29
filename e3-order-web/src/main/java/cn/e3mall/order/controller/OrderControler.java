package cn.e3mall.order.controller;
/**
 * 订单controller
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月28日 下午5:27:37
*/

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.e3mall.cart.service.CartService;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbUser;


@Controller
public class OrderControler {
	@Autowired
	private CartService cartService;
	
	/**
	 * 跳转到订单页面
	 * @return
	 */
	@RequestMapping("/order/order-cart")
	public String toOrderPage(HttpServletRequest request) {
		//获得当前登陆的用户
		TbUser user = (TbUser) request.getAttribute("user");
		//查询到购物车商品列表，转发到订单页面
		List<TbItem> cartList = cartService.selectCartByUserId(user.getId());
		request.setAttribute("cartList", cartList);
		return "order-cart";
	}
	
	
	
}
