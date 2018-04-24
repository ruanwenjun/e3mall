package cn.e3mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.dao.SearchDao;
import cn.e3mall.search.service.SearchItemService;

/**
 * @author ruanwenjun E-mail:861923274@qq.com
 * @date 2018年4月22日 下午8:57:00
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {
	@Autowired
	private SearchDao searchDao;

	public SearchResult searchItemsByTitle(String title, int pageSize, int page) throws Exception{

		// 封装查询对象
		SolrQuery query = new SolrQuery();
		query.setQuery(title);
		// 设置默认查询域
		query.set("df", "item_title");
		// 计算起始页
		int start = (page - 1) * pageSize;
		// 设置分页
		query.setStart(start);
		query.setRows(pageSize);
		// 开启高亮
		query.setHighlight(true);
		query.setHighlightSimplePre("<em style=\"color:red;\">");
		query.setHighlightSimplePost("</em>");
		// 调用dao查询
		SearchResult results = searchDao.search(query);

		//测试异常
		//int a = 1/0;
		
		// 计算总页数
		int totalPages = (int) ((results.getRecourdCount() + pageSize - 1) / pageSize);
		results.setTotalPages(totalPages);
		return results;

	}

}
