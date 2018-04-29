package cn.e3mall.order.interceptor;

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
 * 
 * 用户登陆拦截器，如果用户没有登陆，则不放行
 * 
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月28日 下午5:38:15
 */
public class UserLoginInterceptor implements HandlerInterceptor {
	@Autowired
	private TokenService tokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从cookie中取token
		String token = CookieUtils.getCookieValue(request, "token");
		// 判断用户是否登陆
		E3Result e3Result = tokenService.findUserByToken(token);
		if (e3Result.getStatus() == 200) {
			// 当前有用户登陆
			TbUser user = (TbUser) e3Result.getData();
			request.setAttribute("user", user);
			return true;
		}
		// 重定向到登陆页面
		response.sendRedirect("http://localhost:8087/page/login?redirect=" + request.getRequestURL());
		// 不放行
		return false;

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
