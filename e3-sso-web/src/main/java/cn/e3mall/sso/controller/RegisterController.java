package cn.e3mall.sso.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.RegisterService;

/**
 * 用户注册controller
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月26日 下午2:11:38
*/
@Controller
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("page/register")
	public String pageRegister() {
		return "register";
	}
	
	/**
	 * 校验注册表单内容是否符合
	 * @param param
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/user/check/{param}/{type}",method=RequestMethod.POST)
	public @ResponseBody E3Result check(@PathVariable String param,@PathVariable String type ) {
		E3Result result = registerService.check(param, type);
		return result;
		
	}
	/**
	 *  进行用户注册
	 * @return
	 */
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	public @ResponseBody E3Result register(TbUser user) {
		E3Result result = registerService.register(user);
		return result;
	}
}
