package cn.e3mall.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.sso.service.TokenService;

/**
 * token Controller
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月26日 下午7:13:30
*/
@Controller
public class TokenController {
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value="user/token/{token}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	private @ResponseBody Object getUserByToken(@PathVariable String token,String callback) {
		//调用服务查询用户
		E3Result result = tokenService.findUserByToken(token);
		//采用jsonp的形式返回
		if(result.getStatus() == 200) {
			MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
			jacksonValue.setJsonpFunction(callback);
			return jacksonValue;
		}
		
		return result;
	}
}
