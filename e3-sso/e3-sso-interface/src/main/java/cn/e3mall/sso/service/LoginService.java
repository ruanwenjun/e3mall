package cn.e3mall.sso.service;
/**
 * 登陆服务接口
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月26日 下午6:21:51
*/

import cn.e3mall.common.pojo.E3Result;

public interface LoginService {
	/**
	 * 根据用户名密码进行登陆
	 * @param username
	 * @param password
	 * @return
	 */
	public E3Result login(String username,String password);
}
