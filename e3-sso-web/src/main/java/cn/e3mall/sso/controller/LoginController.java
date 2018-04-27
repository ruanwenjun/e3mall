package cn.e3mall.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.sso.service.LoginService;

/**
 * 登陆controller
 * 
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月26日 下午3:34:48
 */
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;

	/**
	 * 跳转到登陆页面
	 * 
	 * @return
	 */
	@RequestMapping("/page/login")
	public String pageLogin() {

		return "login";
	}

	/**
	 * 用户登陆
	 * 
	 * @return
	 */
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	public @ResponseBody E3Result login(String username, String password, HttpServletRequest request,
			HttpServletResponse response) {
		// 调用服务进行登陆
		E3Result e3Result = loginService.login(username, password);
		// 取出redis中的key
		String key = (String) e3Result.getData();
		// 将key写入cookie
		CookieUtils.setCookie(request, response, "token", key);

		return e3Result;
	}

}
