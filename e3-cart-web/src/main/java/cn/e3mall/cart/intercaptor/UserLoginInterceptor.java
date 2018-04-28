package cn.e3mall.cart.intercaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;

/**
 * 查询是否用户登陆拦截器
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月28日 上午9:43:28
*/
public class UserLoginInterceptor implements HandlerInterceptor {
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 如果当前存在用户登陆，那么将登陆的用户信息存入request
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//从cookie中取得token
		String token = CookieUtils.getCookieValue(request, "token");
		//判断token是否存在
		if(StringUtils.isNotBlank(token)) {
			//从redis中取得token
			E3Result e3Result = tokenService.findUserByToken(token);
			// 如果用户没有过期
			if (e3Result.getStatus() == 200 ) {
				TbUser user = (TbUser) e3Result.getData();
				//将用户加入到request
				request.setAttribute("user", user);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
