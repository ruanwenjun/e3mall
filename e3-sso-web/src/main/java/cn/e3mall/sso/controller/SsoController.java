package cn.e3mall.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月25日 下午4:48:33
*/
@Controller
public class SsoController {

	@RequestMapping("user/register")
	public String toSignUp() {
		return "register";
	}
}
