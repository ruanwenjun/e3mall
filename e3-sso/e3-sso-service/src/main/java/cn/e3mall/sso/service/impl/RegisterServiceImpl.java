package cn.e3mall.sso.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.dao.TbUserMapper;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.pojo.TbUserExample;
import cn.e3mall.pojo.TbUserExample.Criteria;
import cn.e3mall.sso.service.RegisterService;

/**
 * 注册服务
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月26日 下午2:21:59
 */
@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private TbUserMapper userMapper;

	@Override
	public E3Result check(String param, String type) {
		// 根据类型选择查询数据库中不同字段
		// 首先判断是否为空串
		if (StringUtils.isBlank(param)) {
			return E3Result.build(500, "信息不能为空");
		}
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// type 为1校验用户名，2校验手机号
		if (type.equals("1")) {
			criteria.andUsernameEqualTo(param);
		} else if (type.equals("2")) {
			criteria.andPhoneEqualTo(param);
		} else {
			return E3Result.build(500, "校验过程出现异常信息");
		}

		List<TbUser> list = userMapper.selectByExample(example);
		// 判断查询结果是否存在
		if (list != null && list.size() > 0) {
			return E3Result.build(204, "该用户已经被注册");
		}
		// 表示可以进行注册
		return E3Result.ok("true");
	}

	@Override
	public E3Result register(TbUser user) {
		// 首先判断用户是否为空
		if (user == null) {
			return E3Result.build(204, "用户信息不符合");
		}
		// 判断用户信息是否为空
		if (StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())
				|| StringUtils.isBlank(user.getPhone())) {

			return E3Result.build(204, "用户输入信息存在为空的项");
		}
		//对密码进行MD5加密
		String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
		user.setPassword(md5Pass);
		// 将信息补全
		user.setCreated(new Date());
		user.setUpdated(new Date());
		// 插入数据库
		userMapper.insertSelective(user);

		return E3Result.build(200, "注册成功");
	}

}
