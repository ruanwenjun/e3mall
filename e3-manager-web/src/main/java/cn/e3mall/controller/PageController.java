package cn.e3mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月17日 下午9:31:46
*/
@Controller
public class PageController {
	
	
	@RequestMapping("/{path}")
	public String toPage(@PathVariable String path) {
		return path;
	}
}
