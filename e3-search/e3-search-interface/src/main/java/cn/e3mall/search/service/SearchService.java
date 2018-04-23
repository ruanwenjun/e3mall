package cn.e3mall.search.service;
/**
 ** 查询服务
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月22日 下午3:32:44
*/

import cn.e3mall.common.pojo.E3Result;

public interface SearchService {
	
	/**
	 * 将所有商品添加到索引库
	 * @return E3Result
	 */
	E3Result addAllToSolr();
}
