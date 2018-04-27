package cn.e3mall.sso.service;

import cn.e3mall.common.pojo.E3Result;
import cn.e3mall.pojo.TbUser;

/**
 * 用户注册服务接口
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月26日 下午2:20:14
*/
public interface RegisterService {
	/**
	 * 校验内容是否符合
	 * @param param
	 * @param type
	 * @return
	 */
	public E3Result check(String param,String type);
	/**
	 * 进行注册，将用户信息补全插入数据库
	 * @param user
	 * @return
	 */
	public E3Result register(TbUser user);
}
