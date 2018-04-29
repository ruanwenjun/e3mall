package cn.e3mall.sso.service;

import cn.e3mall.common.pojo.E3Result;

/**
 * 根据ToKen查询用户服务接口
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月26日 下午7:05:30
 */
public interface TokenService {
	/**
	 * 根据token从redis中查询用户信息
	 * @param token
	 * @return
	 */
	public E3Result findUserByToken(String token);
}
