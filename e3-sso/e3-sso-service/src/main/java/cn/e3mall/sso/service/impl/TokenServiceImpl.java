package cn.e3mall.sso.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.TokenService;

/**
 * 根据Token查询用户信息服务
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月26日 下午7:07:04
*/
@Service
public class TokenServiceImpl implements TokenService {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Override
	public E3Result findUserByToken(String token) {
		//判断token是否为空
		if(StringUtils.isBlank(token)) {
			return E3Result.build(204, "用户登陆已过期，请重新登陆");
		}
		//查询用户信息
		String userInfo = jedisClient.get("USER_SESSION:"+token);
		TbUser user = JsonUtils.jsonToPojo(userInfo, TbUser.class);
		//用户信息已经失效
		if(StringUtils.isBlank(userInfo)) {
			return E3Result.build(204, "用户登陆已过期，请重新登陆");
		}
		
		//更新redis中用户信息的过期时间
		jedisClient.expire("USER_SESSION:"+token, 1800);
		
		return E3Result.ok(user);
	}

}
