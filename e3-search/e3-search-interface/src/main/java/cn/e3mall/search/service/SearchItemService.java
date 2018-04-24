package cn.e3mall.search.service;


import cn.e3mall.common.pojo.SearchResult;

/**
 * 前台页面查询商品的服务
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月22日 下午8:53:30
*/
public interface SearchItemService {
	
	/**
	 * 根据title查询商品
	 * @param title
	 * @param pageSize
	 * @param start
	 * @return
	 */
	 
	SearchResult searchItemsByTitle(String title,int pageSize,int start) throws Exception;
}
