package cn.e3mall.sso.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.dao.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.LoginService;

/**
 * 登陆服务
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月26日 下午6:22:49
*/
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private TbUserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;
	
	
	@Override
	public E3Result login(String username, String password) {
		// 先判断用户名密码是否为空
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return E3Result.build(204, "用户名或密码为空");
		}
		//根据用户名从数据库中查询用户信息
		TbUserExample example = new TbUserExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<TbUser> list = userMapper.selectByExample(example);
		if(list == null || list.size() <= 0) {
			return E3Result.build(204, "用户名或密码错误");
		}
		
		//判断密码是否正确
		TbUser user = list.get(0);
		password = DigestUtils.md5DigestAsHex(password.getBytes());
		if(!user.getPassword().equals(password)) {
			return E3Result.build(204, "用户名或密码错误");
		}
		
		//校验登陆成功
		user.setPassword(null);
		String userJson = JsonUtils.objectToJson(user);
		//生成redis中的key
		String key = UUID.randomUUID().toString();
		//将用户信息保存到redis中并设置过期时间
		jedisClient.set("USER_SESSION:"+key, userJson);
		jedisClient.expire(key, 1800);
		
		return E3Result.ok(key);
	}

}
