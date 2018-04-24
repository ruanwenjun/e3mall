package cn.e3mall.search.dao;
/**
 * 前台页面搜索商品的Dao
 * @author ruanwenjun 
 *		   E-mail:861923274@qq.com
 * @date 2018年4月22日 下午8:25:41
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;

@Controller
public class SearchDao {
	@Autowired
	private SolrServer solrServer;

	// 根据条件查询商品
	public SearchResult search(SolrQuery query) throws SolrServerException {

		SearchResult result = new SearchResult();
		List<SearchItem> itemList = new ArrayList<>();

		QueryResponse response = solrServer.query(query);
		SolrDocumentList results = response.getResults();
		// 获得高亮结果集
		Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
		// 获得总结果数
		long numFound = results.getNumFound();
		result.setRecourdCount(numFound);
		// 封装商品list
		for (SolrDocument solrDocument : results) {
			SearchItem item = new SearchItem();
			item.setId((String) solrDocument.getFieldValue("id"));
			item.setCategoryName((String) solrDocument.getFieldValue("item_category_name"));
			item.setImage((String) solrDocument.getFieldValue("item_image"));
			item.setPrice((Long) solrDocument.getFieldValue("item_price"));
			item.setSellPoint((String) solrDocument.getFieldValue("item_sell_point"));
			// 封装商品title
			Map<String, List<String>> map = highlighting.get(item.getId());
			List<String> list = map.get("item_title");
			// 判断高亮结果中是否有商品title
			if (list != null && list.size() > 0) {
				item.setTitle(list.get(0));
			} else {
				item.setTitle((String) solrDocument.getFieldValue("item_title"));
			}
			// 将封装的商品加到list
			itemList.add(item);
		}
		
		result.setItemList(itemList);
		
		return result;

	}
}
